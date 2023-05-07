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


// 즐겨찾기   
let mybookmark = document.querySelector(".favorite");
mybookmark.addEventListener('click', function () {
  fetch("myfavorite")
    .then(response => response.json())
    .then(data => {
      console.log(data);

      let tableHtml =  
      `<div class="why-us-content">
      <table>
          <tr>
             <th>번호</th>
             <th>시설이름</th>
             <th>시설주소</th>
          </tr>`
        for (let i = 0; i < data.length; i++) {
        tableHtml +=  
        `<tr>
            <td><a style="color: #d8a899;">${data[i].facNum}</a></td>
            <td><a href="#" class="postTitle" style="color: #d8a899;">${data[i].favName}</a></td>
            <td><a style="color: #d8a899;">${data[i].favContent}</a></td>
          </tr>`;
          }
       tableHtml += `</table></div>`;
       document.querySelector(".right").innerHTML = tableHtml;
    })
    .catch(error => {
      console.error(error);
    });
   });
   
   
// 내가 쓴 게시글
let mypost = document.querySelector(".my_post");
mypost.addEventListener('click', function () {
  fetch("myPost")
    .then(response => response.json())
    .then(data => {
      console.log(data);

      let tableHtml =  
      `<div class="why-us-content">
      <table>
          <tr>
             <th>글번호</th>
             <th>글제목</th>
           <th>작성일자</th>
             <th>조회수</th>
          </tr>`
        for (let i = 0; i < data.length; i++) {
        tableHtml +=  
        `<tr>
            <td><a style="color: #d8a899;">${data[i].postNum}</a></td>
            <td><a href="#" class="postTitle" style="color: #d8a899;">${data[i].postTitle}</a></td>
            <td><a style="color: #d8a899;">${data[i].postDate}</a></td>
            <td><a style="color: #d8a899;">${data[i].postViews}</a></td>
          </tr>`;
          }
       tableHtml += `</table></div>`;
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
      `<div class="why-us-content">
      <table>
          <tr>
             <th>댓글내용</th>
             <th>작성일자</th>
          </tr>`
        for (let i = 0; i < data.length; i++) {
        tableHtml +=  
        `<tr>
            <td><a style="color: #d8a899;">${data[i].commentContent}</a></td>
            <td><a class="postTitle" style="color: #d8a899;">${data[i].commentDate}</a></td>
          </tr>`;
          }
       tableHtml += `</table></div>`;
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
      `<div class="why-us-content">
      <table>
          <tr>
             <th>리뷰번호</th>
             <th>리뷰내용</th>
             <th>작성일자</th>
             <th>별점</th>
          </tr>`
        for (let i = 0; i < data.length; i++) {
        tableHtml +=  
        `<tr>
            <td><a style="color: #d8a899;">${data[i].reviewNum}</a></td>
            <td><a style="color: #d8a899;">${data[i].reviewInfo}</a></td>
            <td><a style="color: #d8a899;">${data[i].reviewDate}</a></td>
            <td><a class="postTitle" style="color: #d8a899;">${data[i].reviewGrade}</a></td>
          </tr>`;
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
      <form action="/pet/mypageEdit" method="post" class="edit_box">
  <p class="subtitle" style="text-align: center">회원정보 수정</p>
  <span>아이디</span><input type="text" name="id" value=${loginId} readonly><br>
  <span>비밀번호</span><input type="password" name="password" placeholder="비밀번호를 입력하세요."><br>
  <input class="w-25 edit_btn" type="submit" value="확인">
</form>`;
   })

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