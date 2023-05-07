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

				document.querySelector(".right").innerHTML = ``;

				let newH2 = document.createElement("h2");
				newH2.innerHTML = "내 예약정보";
				document.querySelector(".right").appendChild(newH2);
				for (let i = 0; i < data.length; i++) {
					document.querySelector(".right").innerHTML +=
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
	    	 <button type="button" class="icon-img03 btn" id="editReserveBtn" data-bs-toggle="modal" data-bs-target="#facilityModal">
			   수정
			 </button>
			    <button type="submit" class="btn">
			      <a>취소
			      <input type="hidden" name="facNum" id ="editId" value="${data[i].facNum}" />
			      <input type="hidden" name="id" id ="" value="${data[i].id}" />
			      </a>
				</button>
		      	</div>
		      </div>
		      </form>
			`;
				}
			})
	});
	
	
	
const modalButton = document.querySelector('#editReserveBtn');
modalButton.addEventListener('click', function(){
  const facilityModal = new bootstrap.Modal(document.getElementById('#facilityModal'));
  facilityModal.show();
});