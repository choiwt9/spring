/*
*
*/
//onload 시점

$(()=>{
    //정보보기 버튼 클릭 시 데이터 요청
    $("#btn1").click(() => {
          getTsunamiShelter(1)
    });
});
//지진해일 긴급대피소 데이터 요청
const getTsunamiShelter = (pageNo) => {

    $.ajax({
        url: 'shelter.do',
        data: {
            rows: $("#rows").val(),
            pageNo: pageNo
        },
        success: (result)=>{
            console.log(result);
            const data = result.TsunamiShelter[1].row; //[{}, {}, {},....]

            let tbodyData = "";
            for(let i in data){
                const item = data[i];

                tbodyData += "<tr>"
                              + "<td>"+ item.shel_nm +"</td>" //대피소명
                              + "<td>"+ item.address +"</td>" // 주소
                              + "<td>"+ item.shel_av +"</td>" // 최대 수용인원
                              + "<td>"+ item.shel_div_type +"</td>" //타입
                              + "<td>"+ item.tel +"</td>" // 연락처
                              + "<td>"+ item.manage_gov_nm +"</td>" //관리기관
                              + "</tr>";
            }
            $("#shelter-list tbody").html(tbodyData);

            const totalCount = result.TsunamiShelter[0].head[0].totalCount;
            const maxPage = Math.ceil(totalCount / $("#rows").val());
            showPagenation(pageNo, maxPage);

        },
        error: (err) => {
            console.log(err);
        }
    });
}
const showPagenation = (currPage, end) => {
    const pageLimit = 10;         //페이징바 표시개수
    let startPage;        //(표시)시작 패이지 번호
    let endPage;        //(표시)마지막 패이지 번호

    startPage = Math.floor((currPage -1)/ pageLimit) * pageLimit + 1;
    endPage = startPage + pageLimit -1;
    if(endPage > maxPage){
        endPage = maxPage;
    }
    /*참고용
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
    */

    let pgData ="";
    //이전버튼
    pgData += "<li class='page-item "
    + (currPage == 1? "disabled" : "")
    + " '><a class='page-link' href='#' onclick='getTsunamiShelter("
    +(currPage-1)
    + ")'>Previous</a></li>";

    //페이징바 번호 버튼
    for(let i=startPage; i<=endPage; i++){
        pgData += '<li class="page-item '
        +(currPage == i ? 'active' : '')
        +'"><a class="page-link" href="#" onclick = "getTsunamiShelter('
        + i
        +')">'+ i + '</a></li>';
    }


    //다음 버튼
    pgData += "<li class='page-item "
                + (currPage == maxPage ? 'disabled' : '')
                +"'><a class='page-link' href='#' onclick='getTsunamiShelter(" 
                + (currPage + 1)
                +")'>Next</a></li>";
   
    $("#page-area").html(pgData);
}
