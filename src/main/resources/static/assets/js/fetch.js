// 이벤트 소스에 이벤트리스너 등록
document.querySelector("#id").addEventListener("keyup", (event) => {
   let inputId = event.target.value;
   if (inputId.length >= 6 && inputId.length <= 8) {
      sendId(inputId);
   } else {
      showIdResultMessage(false);
   }
});

function sendId(id) {
   let option = {
      method: "post",
      headers: {
         "Content-Type": "application/x-www-form-urlencoded"
      },
      body: `id=${id}`
   };

   fetch("/pet/idcheck", option)
      .then(respose => respose.json())
      .then(usable => showIdResultMessage(usable))
      .catch(error => console.log(error));
}

function showIdResultMessage(usable) {
   let messageDiv = document.getElementById("id-message");
   if (usable) {
      messageDiv.innerHTML = "<span style='color:blue'>사용 가능한 아이디입니다.</span>";
   } else {
      messageDiv.innerHTML = "<span style='color:red'>사용하실 수 없는 아이디입니다.</span>";
   }
}
