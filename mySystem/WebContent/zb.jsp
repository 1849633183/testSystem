<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
 <HEAD>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
      <link rel="stylesheet" href="css/login/css/style.css">
      		<link type="text/css" rel="stylesheet" href="css/src/jquery.toast.css">
      
      
  <TITLE>学生管理</TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
  body {
  background: #060e1b;
  overflow: hidden;
}

canvas {
  //opacity: 0.5;
}
  </style>
  
 </HEAD>

 <BODY>
 <script type="text/javascript" src="css/js/jquery-1.11.0.min.js"></script>  
<script type="text/javascript" src="css/src/jquery.toast.js"></script>

 <center><img src="img/planet.jpg" width="280px"></center>
 <div class="form" style="z-index:10;position:absolute;left:750px;top:160px;">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">注册</a></li>
        <li class="tab"><a href="#login">登陆</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>欢迎注册！</h1>
          
          <form action="RegisterServlet" method="post"  accept-charset="utf-8">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                昵称<span class="req">*</span>
              </label>
              <input type="text" name="name" required autocomplete="off" />
            </div><br>
        
          </div>

          <div class="field-wrap">
            <label>
              Email 邮箱<span class="req">*</span>
            </label>
            <input type="email" name="email"required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              设置密码<span class="req">*</span>
            </label>
            <input type="password" name="password"required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block"/>确定注册</button>
          
          </form>

        </div>
        
        <div id="login">   
          <h1>欢迎登陆！</h1>
          
          <form action="LoginServlet" method="post"  accept-charset="utf-8">
          
            <div class="field-wrap">
            <label>
              Email 地址<span class="req">*</span>
            </label>
            <input type="email"name="email" value="${cookie.name.value }"required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              密码<span class="req">*</span>
            </label>
            <input type="password"name="password"value="${cookie.password.value }"required autocomplete="off"/>
          </div>
          <input type="checkbox" value="yes" name="remember"><p style="color:white" align="center">记住密码</p></input>
          <!-- <p class="forgot"><a href="#">忘记密码?</a></p> -->
          
          <button class="button button-block"/>登陆</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->



<c:if test="${requestScope.error!=null}">
		<script type="text/javascript">
		$.toast({
			  text
			 : "登陆失败!",
			  showHideTransition
			 : 'slide',
			  bgColor
			 : 'pink',

			  textColor
			 : 'black',

			  allowToastClose
			 : false,

			  hideAfter
			 : 3000,

			  stack
			 : 3,

			  textAlign
			 : 'left',

			  position
			 : 'top-right'

			})
		</script>
	</c:if>
	<c:if test="${requestScope.RegisterError!=null}">
		<script type="text/javascript">
		$.toast({
			  text
			 : "该邮箱已被注册!",
			  showHideTransition
			 : 'slide',
			  bgColor
			 : 'green',

			  textColor
			 : 'white',

			  allowToastClose
			 : false,

			  hideAfter
			 : 3000,

			  stack
			 : 3,

			  textAlign
			 : 'left',

			  position
			 : 'top-right'

			})
		</script>

	</c:if>

  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script  src="css/login/js/index.js"></script>
 
 
 <canvas id="canvas"></canvas>
  <script>
  "use strict";

var canvas = document.getElementById('canvas'),
  ctx = canvas.getContext('2d'),
  w = canvas.width = window.innerWidth,
  h = canvas.height = window.innerHeight,
    
  hue = 217,
  stars = [],
  count = 0,
  maxStars = 1400;

// Thanks @jackrugile for the performance tip! https://codepen.io/jackrugile/pen/BjBGoM
// Cache gradient
var canvas2 = document.createElement('canvas'),
    ctx2 = canvas2.getContext('2d');
    canvas2.width = 100;
    canvas2.height = 100;
var half = canvas2.width/2,
    gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);
    gradient2.addColorStop(0.025, '#fff');
    gradient2.addColorStop(0.1, 'hsl(' + hue + ', 61%, 33%)');
    gradient2.addColorStop(0.25, 'hsl(' + hue + ', 64%, 6%)');
    gradient2.addColorStop(1, 'transparent');

    ctx2.fillStyle = gradient2;
    ctx2.beginPath();
    ctx2.arc(half, half, half, 0, Math.PI * 2);
    ctx2.fill();

// End cache

function random(min, max) {
  if (arguments.length < 2) {
    max = min;
    min = 0;
  }
  
  if (min > max) {
    var hold = max;
    max = min;
    min = hold;
  }

  return Math.floor(Math.random() * (max - min + 1)) + min;
}

var Star = function() {

  this.orbitRadius = random(w / 2 - 50);
  this.radius = random(100, this.orbitRadius) / 10;
  this.orbitX = w / 2;
  this.orbitY = h / 2;
  this.timePassed = random(0, maxStars);
  this.speed = random(this.orbitRadius) / 100000;
  this.alpha = random(2, 10) / 10;

  count++;
  stars[count] = this;
}

Star.prototype.draw = function() {
  var x = Math.sin(this.timePassed + 1) * this.orbitRadius + this.orbitX,
      y = Math.cos(this.timePassed) * this.orbitRadius/2 + this.orbitY,
      twinkle = random(10);

  if (twinkle === 1 && this.alpha > 0) {
    this.alpha -= 0.05;
  } else if (twinkle === 2 && this.alpha < 1) {
    this.alpha += 0.05;
  }

  ctx.globalAlpha = this.alpha;
    ctx.drawImage(canvas2, x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);
  this.timePassed += this.speed;
}

for (var i = 0; i < maxStars; i++) {
  new Star();
}

function animation() {
    ctx.globalCompositeOperation = 'source-over';
    ctx.globalAlpha = 0.8;
    ctx.fillStyle = 'hsla(' + hue + ', 64%, 6%, 1)';
    ctx.fillRect(0, 0, w, h)
  
  ctx.globalCompositeOperation = 'lighter';
  for (var i = 1, l = stars.length; i < l; i++) {
    stars[i].draw();
  };  
  
  window.requestAnimationFrame(animation);
}

animation();
  </script>
 </BODY>
</HTML>
