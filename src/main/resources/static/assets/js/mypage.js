// 회원 탈퇴
let deleteinfo = document.querySelector(".delete_info");
deleteinfo.addEventListener('click',
   function api2() {

      document.querySelector(".right").innerHTML = `
        <form action="/pet/mypageDelete" class="edit_box" method="post">
             <input type="hidden" name="_method" value="delete" />
        <p class="subtitle" style="text-align: center">회원탈퇴</p>
        <span>아이디</span><input type="text" name="id" value=${loginId} readonly><br>
        <span>비밀번호</span><input type="password" name="password" placeholder="비밀번호를 입력하세요"><br>
      
        <input class="w-25 edit_btn" type="submit" value="탈퇴하기">
      </form>`;

   })

function api() {
   fetch("https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc?page=1&perPage=20&serviceKey=wgEpazA4adJUsEbbNdAFoSgZQwnqjFz7rjBWXasSoEz3H0tpc3j%2FRMgcyk2poT0OOespsnugSKo1%2BFDQPzS6Ug%3D%3D")
      .then(function(response) {
         if (response.status != 200) {
            console.log("서버와 통신에 실패")
         } else {
            return response.json();
         }
      }).then(function(jsonData) {
         var data = jsonData.data;
         console.log(data);

         for (var i = 0; i < data.length; i++) {
            console.log(data[i]['시설명'])
         }

         const title = document.createElement("p");
         title.textContent = data[0]['시설명'];
         document.querySelector('.favorite_box').appendChild(title);

         const tel = document.createElement("p");
         tel.textContent = data[0]['전화번호'];
         document.querySelector('.favorite_box').appendChild(tel);

         const add = document.createElement("p");
         add.textContent = data[0]['도로명주소'];
         document.querySelector('.favorite_box').appendChild(add);
      })
}


let favorite = document.querySelector(".favorite");
favorite.addEventListener('click',
   function api2() {
      fetch("https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc?page=1&perPage=20&serviceKey=wgEpazA4adJUsEbbNdAFoSgZQwnqjFz7rjBWXasSoEz3H0tpc3j%2FRMgcyk2poT0OOespsnugSKo1%2BFDQPzS6Ug%3D%3D")
         .then(function(response) {
            if (response.status != 200) {
               console.log("서버와 통신에 실패")
            } else {
               return response.json();
            }
         }).then(function(jsonData) {
            var data = jsonData.data;
            console.log(data);

            document.querySelector(".right").innerHTML = `
      <p class="subtitle" style="text-align: center">즐겨찾기</p>
      <div class="favorite_box">
      <div class="favorite_img">
        <img src="assets/img/banner2.jpeg" alt="">
      </div>
      <p>${data[0]['시설명']}</p>
      <p>${data[0]['전화번호']}</p>
      <p>${data[0]['도로명주소']}</p>
      <p class="subtitle" style="text-align: center">즐겨찾기</p>
      <div class="favorite_box">
      <div class="favorite_img">
        <img src="assets/img/banner2.jpeg" alt="">
      </div>
      <p>${data[1]['시설명']}</p>
      <p>${data[1]['전화번호']}</p>
      <p>${data[1]['도로명주소']}</p>
    </div>`;

            for (var i = 0; i < data.length; i++) {
               console.log(data[i]['시설명'])
            }
         })
   })


// 내가 쓴 게시글
let mypost = document.querySelector(".my_post");
mypost.addEventListener('click', function () {
  fetch("myPost")
    .then(response => response.json())
    .then(data => {
      console.log(data);

      let tableHtml =  
      `<!-- 게시글리스트 출력 -->
      <div class="why-us-content p-5" > 
			<label class="w-100">
				<table class="w-100">
					<tr>
						<th>카테고리</th>
						<th style="width: 30%;">글제목</th>
						<th>닉네임</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>`
					for (let i = 0; i < data.length; i++) {
        			tableHtml +=  
					`
						<tr>
							<!-- 게시글의 번호를 출력합니다 -->
							<td><a>${data[i].postType}</a></td>
							<td><a style="color: #d8a899;"
								   href="/board/detail/${data[i].postNum}">${data[i].postTitle}</a></td>
							<td><a>${data[i].id}</a></td>
							<td><a>${data[i].postDate}</a></td>
							<td><a>${data[i].postViews}</a></td>
						</tr>
						`}tableHtml +=`
				</table>
			</label>
		<div>
      `
      
      
       document.querySelector(".right").innerHTML = tableHtml;
    })
    .catch(error => {
      console.error(error);
    });
   });


// 내가 쓴 댓글
let mycomment = document.querySelector(".my_comment");
mycomment.addEventListener('click', function () {
  fetch("myComment")
    .then(response => response.json())
    .then(data => {
      console.log(data);

      let tableHtml =  
      ` <div class="why-us-content p-5" > 
			<label class="w-100">
				<table class="w-100">
					<tr>
						<th>댓글내용</th>
						<th>작성일</th>
						<th style="width: 30%;">게시글바로가기</th>
					</tr>`
					 for (let i = 0; i < data.length; i++) {
        			tableHtml +=
					`
					<tr>
							<td><a>${data[i].commentContent}</a></td>
							<td><a>${data[i].commentDate}</a></td>
							<td><a style="color: #d8a899;"
								   href="/board/detail/${data[i].postNum}">바로가기</a></td>
					</tr>
`;
          }
       tableHtml += `</table></label></div>`;
       document.querySelector(".right").innerHTML = tableHtml;
    })
    .catch(error => {
      console.error(error);
    });
   });
   
   
// 내가 쓴 리뷰
let myreview = document.querySelector(".my_review");
myreview.addEventListener('click', function () {
  fetch("myReview")
    .then(response => response.json())
    .then(data => {
      console.log(data);

      let tableHtml =  
      `<div class="why-us-content p-5" > 
			<label class="w-100">
				<table class="w-100">
					<tr>
						<th>리뷰번호</th>
						<th style="width: 30%;">리뷰정보</th>
						<th>작성일</th>
						<th>점수</th>
						<th>시설바로가기</th>
					</tr>`
        for (let i = 0; i < data.length; i++) {
        tableHtml +=  
        `
        	<tr>
							<!-- 게시글의 번호를 출력합니다 -->
							<td><a>${data[i].reviewNum}</a></td>
							<td><a>${data[i].reviewInfo}</a></td>
							<td><a>${data[i].reviewDate}</a></td>
							<td><a>${data[i].reviewGrade}</a></td>
							<td><a style="color: #d8a899;"
								   href="/facilities/detail/${data[i].facNum}">바로가기</a></td>
					`;
          }
       tableHtml += `</table></div>`;
       document.querySelector(".right").innerHTML = tableHtml;
    })
    .catch(error => {
      console.error(error);
    });
   });      








// 회원정보 수정
let loginId = document.querySelector("#loginId").value;
let edit = document.querySelector(".edit_info");

edit.addEventListener('click',
   function api2() {
      document.querySelector(".right").innerHTML = `
 <form id="editForm" method="post">
  <p class="subtitle" style="text-align: center">회원정보 수정</p>
  <span>아이디</span><input type="text" name="id" id="editId" value=${loginId} readonly><br>
  <span>비밀번호</span><input type="password" name="password" id="editPw" placeholder="비밀번호를 입력하세요."><br>
  <input id="subbtn" type="submit" class="w-25 edit_btn" value="확인" data-bs-toggle="modal" data-bs-target="#editModal">
  </form>
`;


document.querySelector(".right").addEventListener('submit', (event) =>{
   event.preventDefault();
   let userId = document.querySelector('#editId')?.value;
   let userPw = document.querySelector('#editPw')?.value;

if (userId !== undefined && userPw !== undefined) {
    // 기존 코드와 동일
    
    let option = {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `id=${userId}&pw=${userPw}`
      };
   
   
   fetch('/pet/mypageEdit', option)
   .then(res => res.json())
   .then(function(data){
      console.log(data);
       if(data===true){
    }else{
      alert('비밀번호가 일치하지 않습니다.');
      document.querySelector('#editModal').innerHTML=` `;
      let referer = document.querySelector("#referer").value;
      location.href = `${referer}`;
    }
      });
   

    
} else {
    console.log('요소를 찾을 수 없습니다.');
}
      
   
      });
      
   });
   
  //const form = document.getElementById('editForm');
    



  
  
document.querySelector('form').addEventListener('submit', submitServer)

  function submitServer(event){
  
     
    const form = event.target;
    let id = form.userId.value;
    let pwd = form.password.value;
    let pwd2 = form.password2.value;
    let name = form.name.value;
    let yy = form.year.value;
    let mm = form.month.value;
    let dd = form.date.value;
    let email = form.email.value;
    let mob = form.mobile.value;
    
     
    if(!id){
      alert("아이디 입력하세요");
      event.prevenDefault();
      return false;
    }

    if(!pwd){
      alert("비밀번호를 입력하세요");
      event.prevenDefault();
      return false;
    }
    if(!pwd2 && pwd2 != pwd){
      alert("비밀번호가 일치하지 않습니다.");
      event.prevenDefault();
      return false;
    }
  //if(!reg.test(pwd.value)){
  //  alert("비밀번호는 영문자, 숫자, 특수문자 조합으로 8~25자리 사용해야합니다.");
  //  return false;
  //}
    if(!name){
      alert("이름을 입력하세요");
      event.prevenDefault();
      return false;
    }
    if(!mm || !yy || !dd){
      alert("생년월일를 선택하세요.");
      event.prevenDefault();
      return false;
    }
    if(yy > 2023){
      alert("2023 이하의 숫자만 가능합니다.");
      event.prevenDefault();
      return false;
    }
    if(mm < 1 || mm > 12){
      alert("1 ~ 12 사이의 숫자여야합니다.");
      event.prevenDefault();
      return false;
    }
    if(dd < 1 || dd > 31){
      alert("1 ~ 31 사이의 숫자여야합니다.");
      event.prevenDefault();
      return false;
    }

    if(!email){
      alert("이메일을 입력하세요.");
      event.prevenDefault();
      return false;
    }

    if(!mob){
      alert("전화번호를 입력하세요");
      event.prevenDefault();
      return false;
    }

  }

  const phone = (target) => {
    target.value = target.value
      .replace(/[^0-9]/g, '')
      .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
   }
