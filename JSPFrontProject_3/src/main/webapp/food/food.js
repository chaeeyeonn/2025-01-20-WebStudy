let food_list=[]
let startPage=0
let endPage=0
let curpage=1
let totalpage=0
window.onload=()=>{
	let input=document.querySelector("#fd")
	input.value='마포'
	dataRecv("마포",curpage)
}
function foodFind(){
	let fd=document.querySelector("#fd").value
	if(fd==="")
	{
		alert("검색어를 입력하세요")
		document.querySelector("#fd").focus()
		return
	}	
	dataRecv(fd,1)
}
function pageChange(page)
{
	let fd=document.querySelector("#fd").value
	dataRecv(fd,page)
}
// 함수 이름이 같으면 오버라이딩 되어 마지막으로 작성된 것으로 실행됨
// 선언적 함수
/*function foodDetail(fno)
{
	
}*/
// 익명의 함수
let detail=(fno)=>{
	let div=document.querySelector("#detail")
	div.style.display=''
	// <div style="display:"> => show
	// <div style="display:none"> => hide
	axios.get('http://localhost/JSPFrontProject_3/food/detail_js.do',{
		params:{
			fno:fno
		}
	}).then((res)=>{
		console.log(res.data)
		let food_detail=res.data
		let html='<img src="'+food_detail.poster+'" style="width:100%">'
		document.querySelector("#poster1").innerHTML=html
		document.querySelector("#title").textContent=food_detail.name
		document.querySelector("#score").textContent=food_detail.score
		document.querySelector("#address").textContent=food_detail.address
		document.querySelector("#phone").textContent=food_detail.phone
		document.querySelector("#type").textContent=food_detail.type
		document.querySelector("#price").textContent=food_detail.price
		document.querySelector("#parking").textContent=food_detail.parking
		document.querySelector("#time").textContent=food_detail.time
		document.querySelector("#theme").textContent=food_detail.them
		document.querySelector("#content").textContent=food_detail.content
	})
}
function dataRecv(fd,page){
	let html='';
		//axios.get() / axios.post()
		axios.get('http://localhost/JSPFrontProject_3/food/find_js.do',{
			params:{
				page:page,
				fd:fd
			}
		})
		.then((response)=>{
			console.log(response.data)
			food_list=response.data
			curpage=response.data[0].curpage
			totalpage=response.data[0].totalpage
			startPage=response.data[0].startPage
			endPage=response.data[0].endPage
			
			console.log("curpage="+curpage)
			console.log("totalpage="+totalpage)
			console.log("startPage="+startPage)
			console.log("endPage="+endPage)
			
			food_list.map(function(vo){
				html+='<div class="col-sm-4">'
					+'<div class="thumbnail">'
					+'<img src="'+vo.poster+'" style="width:100%" onclick="detail('+vo.fno+')">'
					+'<p>'+vo.name+'<p>'
					+'</div>'
					+'</div>'
			})
			let main=document.querySelector("#poster");
			//CSS selector
			main.innerHTML=html
			
			let pages=document.querySelector("#pages")
			let pp='<ul class="pagination">'
			if(startPage>1)
				pp+='<li><a onclick="pageChange('+(startPage-1)+')">&lt;</a></li>'
			for(let i=startPage;i<=endPage;i++)
			{
				let style=''
				if(i==curpage)
					style='class=active'
				pp+='<li '+style+'><a onclick="pageChange('+i+')">'+i+'</a></li>'		
			}
			if(endPage<totalpage)
				pp+='<li><a onclick="pageChange('+(endPage+1)+')">&gt;</a></li>'
			pp+='</ul>'
			
			pages.innerHTML=pp
		})
}