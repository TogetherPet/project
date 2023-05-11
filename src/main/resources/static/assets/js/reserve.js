/*시설 예약 섹션 스크립트*/
$(function() {
$('input[name="daterange"]').daterangepicker({
  opens: 'left'
}, function(start, end, label) {
  console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
});
});


document.querySelector(".my_reserve").addEventListener('click',

   function requestMyReserve() {
    fetch('myreserve')
        .then(response => {
            return response.json();
        })
        .then(data => {
            console.dir(data);
            console.log(data.length);
         
            // 새로운 내용을 추가할 HTML을 담을 변수
            let newHTML = document.querySelector(".right").innerHTML = ``;

            // 기존에 있던 HTML을 추가할 변수에 먼저 저장
            let currentHTML = document.querySelector(".right").innerHTML;

            let newH2 = document.createElement("h2");
            newH2.innerHTML = "내 예약정보";
            newHTML += newH2.outerHTML;

            for (let i = 0; i < data.length; i++) {
                newHTML +=
                    `
                    <form action="/pet/delete/reserve">
                      <div class="favorite_box">
                        <a href="/facilities/detail/${data[i].facNum}">
                          <div class="favorite_img">
                            <img src="/assets/img/banner2.jpeg" alt="">
                          </div>
                          <div>
                            <h3 style="
                            width: 90%;
                            text-overflow: ellipsis;
                            white-space: nowrap;
                            word-wrap: break-word;">${data[i].facName}</h3>
                            <p style="margin: 0; text-align: left;">예약날짜 : ${data[i].resDate}</p>
                            <p style="margin: 0; text-align: left;">예약인원 : ${data[i].resPeoples}</p>
                          </div>
                        </a>
                        <button type="button" class="icon-img03 btn" id="editBtn"
                          data-resNum="${data[i].resNum}"
                          data-resDate="${data[i].resDate}"
                          data-resPeoples="${data[i].resPeoples}"
                          data-facNum="${data[i].facNum}"
                          onclick="openModal(this)">
                      수정
                  </button>
                        <button type="submit" class="btn">
                          <a>취소
                          <input type="hidden" name="facNum" id ="editId" value="${data[i].facNum}" />
                          <input type="hidden" name="id" id ="" value="${data[i].id}" />
                          </a>
                        </button>
                      </div>
                    </form>
                    `;
            }

            // 기존의 innerHTML과 새로운 내용을 추가한 HTML을 합쳐서 출력
            document.querySelector(".right").innerHTML = currentHTML + newHTML;
            console.log(data);
        })
        .catch(error => {
            console.error(error);
        });
});

function openModal(button) {
   const resNum = document.getElementById("editBtn").getAttribute('data-resNum');
   const resDate = document.getElementById("editBtn").getAttribute('data-resDate');
   const resPeoples = document.getElementById("editBtn").getAttribute('data-resPeoples');
   const facNum = document.getElementById("editBtn").getAttribute('data-facNum');
   console.log(document.querySelector("#editBtn").dataset);
   console.log(resNum);
   console.log(resDate);
   console.log(resPeoples);
   
    // 모달 창 열기
    var myModal = new bootstrap.Modal(document.getElementById('facilityModal'));
    myModal.show();

    // 값 채우기
    document.getElementById('resNum').value = resNum;
    document.getElementById('resDate').value = resDate;
   document.getElementById('resPeoples').value = resPeoples;
   document.getElementById('facNum').value = facNum;
}
   