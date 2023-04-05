function api(){
fetch("https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc?page=1&perPage=20&serviceKey=wgEpazA4adJUsEbbNdAFoSgZQwnqjFz7rjBWXasSoEz3H0tpc3j%2FRMgcyk2poT0OOespsnugSKo1%2BFDQPzS6Ug%3D%3D")
  .then(function(response){
    if(response.status != 200){
      console.log("서버와 통신에 실패")
    }else{
      return response.json();
    }
  }).then(function(jsonData){
    var data = jsonData.data;
    console.log(data);

    for(var i = 0; i < data.length; i++){
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
function api2(){
  fetch("https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc?page=1&perPage=20&serviceKey=wgEpazA4adJUsEbbNdAFoSgZQwnqjFz7rjBWXasSoEz3H0tpc3j%2FRMgcyk2poT0OOespsnugSKo1%2BFDQPzS6Ug%3D%3D")
    .then(function(response){
      if(response.status != 200){
        console.log("서버와 통신에 실패")
      }else{
        return response.json();
      }
    }).then(function(jsonData){
      var data = jsonData.data;
      console.log(data);
      
      document.querySelector(".right").innerHTML=`
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

      for(var i = 0; i < data.length; i++){
        console.log(data[i]['시설명'])
      }
    })
  })

  let mypost = document.querySelector(".my_post");
mypost.addEventListener('click', 
function api2(){
      document.querySelector(".right").innerHTML=`
      <p class="subtitle" style="text-align: center">내가 쓴 게시글</p>
      <div class="myboard">
  <ul class="board_title">
    <li class="lititle">번호
      <ul>
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
      </ul>
    </li>
    <li class="lititle">게시글 내용
      <ul>
        <li>게시글1</li>
        <li>게시글2</li>
        <li>게시글3</li>
        <li>게시글4</li>
        <li>게시글5</li>
      </ul>
    </li>
    <li class="lititle">작성일자
      <ul>
        <li>23.03.24</li>
        <li>23.03.24</li>
        <li>23.03.24</li>
        <li>23.03.24</li>
        <li>23.03.24</li>
      </ul>
    </li>
    <li class="lititle">조회수
      <ul>
        <li>30</li>
        <li>30</li>
        <li>30</li>
        <li>30</li>
        <li>30</li>
      </ul>
    </li>
  </ul>
  
</div>`;
  })


  let edit = document.querySelector(".edit_info");
edit.addEventListener('click', 
function api2(){
      document.querySelector(".right").innerHTML=`
      <form class="edit_box">
  <p class="subtitle" style="text-align: center">회원정보 수정</p>
  <span>아이디</span><input type="text" readonly><br>
  <span>비밀번호</span><input type="password" placeholder="변경하실 비밀번호를 입력하세요"><br>
  <span>비밀번호 확인</span><input type="password" placeholder="변경하실 비밀번호를 입력하세요"><br>

  <input class="w-25 edit_btn" type="submit" value="수정하기">
  <input class="w-25 edit_btn" type="button" value="취소">
</form>`;
  })


  let deleteinfo = document.querySelector(".delete_info");
  deleteinfo.addEventListener('click', 
  function api2(){
    
        document.querySelector(".right").innerHTML=`
        <form class="edit_box">
        <p class="subtitle" style="text-align: center">회원탈퇴</p>
        <span>아이디</span><input type="text" readonly><br>
        <span>비밀번호</span><input type="password" placeholder="비밀번호를 입력하세요"><br>
        <span>비밀번호 확인</span><input type="password" placeholder="비밀번호를 입력하세요"><br>
      
        <input class="w-25 edit_btn" type="submit" value="탈퇴하기">
        <input class="w-25 edit_btn" type="button" value="취소">
      </form>`;
  
    })