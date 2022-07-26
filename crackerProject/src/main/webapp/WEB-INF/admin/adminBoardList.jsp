<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type = "text/css">
.subjectA:link {color: black; text-decoration: none;}
.subjectA:visited {color : black; text-decoration: none;}
.subjectA:hover {color: blue; text-decoration: underline;}
.subjectA:active {color: #d81e5b; text-decoration: none;}
#samePage {color: red; text-decoration: none;}
#otherPage {color: black; text-decoration: none;}
#currentPaging{
	color:red;
	text-decoration:underline;
	cursor: pointer;
}
#paging{
	color:black;
	text-decoration:non;
	cursor: pointer;
}

#boardListDiv{
/* 	border: 1px solid red; */
	margin: 30px 30px;
	width: 80%;
	height: 400px;
	background-color: #ffffff; 
	box-shadow: 0 1px 3px 0 rgb(0 0 0 / 15%);
/* 	border: solid 1px #98a0a7; */
}

#boardListDiv .goodCount{
/* 	border: 1px red solid; */
	float: left;
	width: 10%;
	margin: 10px;
	color: #98a0a7;
}

#boardListDiv .subjcet{
/* 	border: 1px red solid; */
	float: left;
	text-align: left;
	margin: 10px;
	width: 40%;
	white-space : nowrap;
 	overflow: hidden;
	text-overflow: ellipsis;
}

#boardListDiv .logtime{
/* 	border: 1px red solid; */
	float: left;
	margin: 10px;
	width: 20%;
	color: #98a0a7;
	
}

#boardListDiv .nickName{
/* 	border: 1px red solid; */
	float: left;
	margin: 10px;
	width: 19%;
	color: #98a0a7;
}

#boardListDiv .listMenu{
/* 	border: 1px red solid; */
	text-align:center;
	height:6%;
	width:95%;
	margin: auto;
	border-bottom:1px solid #ebeef1;
}
</style>
</head>
<body>
	<div id="boardListDiv">
	
	</div>
	
	
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	
	var boardUrl;
	if($('#keyword').val()==null){
		boardUrl = '/index/board/getBoardList';
	}else{
		boardUrl = '/index/board/boardSearch';
	}
	$.ajax({
		type : 'post',
		url : boardUrl,
		data : {'pg' : $('#pg').val(),
				'keyword' : $('#keyword').val()},
		dataType : 'json',
		success : function(data){
			$.each(data.list, function(index, items){
				//alert(JSON.stringify(data));
				//alert(data.memLogin);
				$('<div/>').addClass('listMenu')
					.append($('<div/>',{
					align: 'center',
					text: items.goodcount,
					class: 'goodCount'
				})).append($('<div/>',{
					class: 'subjcet'
					}).append($('<a/>',{
						href:'#',
						text: items.subject,
						class: 'subjectA subjectA_'+items.seq
					}))
				).append($('<div/>',{
					class: 'logtime',
					align: 'center',
					text: items.logtime.toLocaleString()
				})).append($('<div/>',{
					class: 'nickName',
					align: 'center',
					text: items.nickname
				})).appendTo($('#boardListDiv'));
				

				$('.subjectA_'+items.seq).click(function(){
						location.href = '/index/board/boardView?seq='
										+items.seq+'&pg='+$('#pg').val();
					
				});
				
				//답글
				for(i=0; i<items.lev; i++){
					$('.subjectA_'+items.seq).before('&emsp;');
				}
				if(items.pseq != 0){
					$('.subjectA_'+items.seq)
						.before(
							$(
								'<img>'
								,{src:'/index/image/reply.gif'
								}
							)
						)
				}//if
				
				
			});//each

			//페이징처리
			$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
		},
		error: function(e){
			 console.log(JSON.stringify(error));
		}
		
	});//ajax
});//function

function boardPaging(pg2) {
	   var keyword = $('#keyword').val();

	   if(keyword == ''){
	      location.href = "/index/board/boardList?pg=" + pg2;
	   }else{
		  $('#searchPg').val(pg2);
		  $('#boardSearchBtn').trigger('click');
		  $('#searchPg').val(1);
	   }
	   
	   
	}
</script>

</body>
</html>