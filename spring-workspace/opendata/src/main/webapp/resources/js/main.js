//비동기 통신(ajax)
        
        //onload 시점
        $(function() {
        	//btn1 아이디 속성을 가지 요소의 클릭 이벤트
        $("#btn1").click(function(){
        /*		//ajax 통신 : 요청 주소 - air.do, 요청 데이터 - {location: 선택된 지역 정보}
				//json 방식으로 데이터를 응답받는 방법
        		$.ajax({
        			url: 'air.do',
        			data: {location:$("#location").val()},
        			success:function(result){
        				console.log(result);
        				//응답 데이터를 tbody에 표시하기
        				const data = result.response.body.items;
        				
        				let bodyData = "";
        				for(let i=0; i<data.length; i++){
        					bodyData += "<tr>"
        					            + "<td>" + data[i].stationName + "</td>"
        					            + "<td>" + data[i].dataTime + "</td>"
        					            + "<td>" + data[i].khaiValue + "</td>"
        					            + "<td>" + data[i].pm10Value + "</td>"
        					            + "<td>" + data[i].so2Value + "</td>"
        					            + "<td>" + data[i].coValue + "</td>"
        					            + "<td>" + data[i].no2Value + "</td>"
        					            + "<td>" + data[i].o3Value + "</td>"
        					            +"</tr>";
        				}
        				$("#ap-table-tbody").html(bodyData);
        			},
        			error: function(err) {
        				console.log(err);
					}
        		});
				*/
				getAirPollution("air.do", 1, function(result){
					console.log(result)
                   //응답데이터를 화면에 표시하는 부분을 함수로 분리

					appendJsonData(result);
		});
			// getAirPollution("air.do", ()=>{})
	});
        	
//btn 아이디 속성을 가진 요소의 클릭 이벤트
			$("#btn2").click(function(){
				//xml 형식으로 응답
				/*
				$.ajax({
					url:'air2.do',
					data: {
						location: $("#location").val()
					},
					success: (result)=> {
                      console.log(result);
					  const itemArr = $(result).find("item");//[<item/>,<item/>,<item/>....]
			
						let tbodyData = "";
						itemArr.each((index, item)=>{
							console.log($(itrem));
						
						tbodyData += "<tr>"
						+"<td>" + $(item).find("stationName").text() + "</td>"
						+"<td>" + $(item).find("dataTime").text() + "</td>"
						+"<td>" + $(item).find("khaiValue").text() + "</td>"
						+ "<td>" + $(item).find("pm10Value").text() + "</td>"
						+ "<td>" + $(item).find("coValue").text() + "</td>"
						+ "<td>" + $(item).find("no2Value").text() + "</td>"
						+ "<td>" + $(item).find("so2Value").text() + "</td>"
						+ "<td>" + $(item).find("o3Value").text() + "</td>"
						+"<tr/>"

					  })

					  $("#ap-table tbody").html(tbodyData);
					  },
					error: (err) => {
						console.log(err);
					}
				
					});
					*/
					getAirPollution("air2.do", 1, (result)=>{
						console.log(result);
	
						
						const itemArr = $(result).find("item");//[<item/>,<item/>,<item/>....]
			
						let tbodyData = "";
						itemArr.each((index, item)=>{
							//console.log($(itrem));
						
						tbodyData += "<tr>"
						+"<td>" + $(item).find("stationName").text() + "</td>"
						+"<td>" + $(item).find("dataTime").text() + "</td>"
						+"<td>" + $(item).find("khaiValue").text() + "</td>"
						+ "<td>" + $(item).find("pm10Value").text() + "</td>"
						+ "<td>" + $(item).find("coValue").text() + "</td>"
						+ "<td>" + $(item).find("no2Value").text() + "</td>"
						+ "<td>" + $(item).find("so2Value").text() + "</td>"
						+ "<td>" + $(item).find("o3Value").text() + "</td>"
						+"<tr/>";

					  });

					  $("#ap-table tbody").html(tbodyData);
					  $("#ap-table + button").remove();
					  if( $("#ap-table tbody tr").length <$(result).find("totalCount").text()){
					  $("#ap-table").after("<button class='btn btn-dark w-100' onclick='getMoreDataXML()'>더 보기</button>");
					  })
					});
			});
	
		

		const getAirPollution = (url, pageNo, callback) => {
			$.ajax({
				url: url,
				data:{
					location: $("#location").val(),
					pageNo: pageNo
				},
				success:(result)=>{
					//매개변수 전달된 콜백함수를 응답데이터를 담아 호출
                          callback(result);
				},
				error: (err) => {
					console.log(err);
				}
			})
		}
		
		const getMoreData = (nextNo) => {
			// 더보기 버튼이 클릭 되었을 때 데이터를 추가로 요청
			getAirPollution("air.do", nextNo, (result)=>{
				appendJsonData(result);
			});
		}

		const appendJsonData = (result)=>{
			const data = result.response.body.items;
					let bodyData=$("#ap-table tbody").html();//처음에는 tbody 부분이 비어 있을 것이고
					                                    //데이터를 조회한 경우에는 조회된 데이터들(tr)이 있을 것임
					for(let item of data){
						//console.log(item);
						bodyData += "<tr>"
        					            + "<td>" + item.stationName + "</td>"
        					            + "<td>" + item.dataTime + "</td>"
        					            + "<td>" + item.khaiValue + "</td>"
        					            + "<td>" + item.pm10Value + "</td>"
        					            + "<td>" + item.so2Value + "</td>"
        					            + "<td>" + item.coValue + "</td>"
        					            + "<td>" + item.no2Value + "</td>"
        					            + "<td>" + item.o3Value + "</td>"
        					            +"</tr>";
					}
					$("#ap-table tbody").html(bodyData);

					$("#ap-table + button").remove();

					if( $("#ap-table tbody tr").length < result.response.body.totalCount){

					
					$("#ap-table").after("<button class='btn btn-dark w-100' onclick='getMoreData("+
						(result.response.body.pageNo+1)+")'>더 보기</button>");
					}
				};
			});

			const getMoreDataXML = (nextNo) => {
				getAirPollution("air2.do", nextNo, function(result)){
					appendXMLData($("#ap-table tbody").html(), result);
				}
			}