<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload = function() {
  const kindWrap =  document.querySelector('.kind_wrap');
  const slider = kindWrap.querySelector('.slider');
  const slideLis = slider.querySelectorAll('li')
  const moveButton = kindWrap.querySelector('.arrow');

  /* ul 넓이 계산해 주기 */
  const liWidth = slideLis[0].clientWidth;
  const sliderWidth = liWidth * slideLis.length;
  slider.style.width = `${sliderWidth}px` ;

  /* 리스너 설치하기 */
  let currentIdx = 0; // 슬라이드 현재 번호
  let translate = 0; // 슬라이드 위치 값
  moveButton.addEventListener('click', moveSlide);

  function moveSlide(event) {
    event.preventDefault();
    if (event.target.className === 'next') {
      if (currentIdx !== slideLis.length -1) {
        translate -= liWidth;
        slider.style.transform = `translateX(${translate}px)`;
        currentIdx += 1;
      }
    } else if (event.target.className === 'prev') {
        if (currentIdx !== 0) {
          translate += liWidth;
          slider.style.transform = `translateX(${translate}px)`;
          currentIdx -= 1;
        }
      }
  }

}
</script>
<style>
* {
  margin:0; 
  padding:0;
}
li {
  list-style: none;
}
.kind_wrap {
  border:2px solid black; 
  width:100%; 
  max-width:800px; 
  margin:0 auto; 
  position: relative;
}
.kind_wrap > .kind_slider {
  overflow: hidden;
}
.kind_wrap > .kind_slider .slider { 
  position: relative; 
  transition: 0.5s;
}
.kind_wrap > .kind_slider .slider li {
  float:left;
}
.kind_wrap > .kind_slider img {
  vertical-align: top;
}
.kind_wrap .arrow > a.prev {
  position: absolute; 
  left:-50px; 
  top:100px;
}
.kind_wrap .arrow > a.next {
  position: absolute; 
  right:-50px; 
  top:100px;
}
</style>
</head>
<body>

<div class="kind_wrap">
  <div class="kind_slider">
    <ul class="slider">
     <c:forTokens items="${vo.images }" delims="," var="img">
	                                    <div class="single-post">
	                                        <!-- Post Thumb -->
	                                        <div class="post-thumb">
	                                            <img src="http://www.menupan.com${img }" alt="">
	                                        </div>
	                                    </div>
                                    </c:forTokens>
        <li><img src="../img/images/man.png" alt=""></li>
        <li><img src="../img/images/woman.png" alt=""></li>
        <li><img src="../img/images/man.png" alt=""></li>
        <li><img src="../img/images/woman.png" alt=""></li>
        <li><img src="../img/images/noimage.png" alt=""></li>
    </ul>
  </div>
  <div class="arrow">
      <a href="" class="prev">이전</a>
      <a href="" class="next">다음</a>
  </div>
</div>

</body>
</html>