

$(document).ready(function() {
	
	var $survey_post = $('#survey_post');
	
	$('#submit_survey').click(function(e) {
		e.preventDefault();
		
		var jsObj = $survey_post.serializeObject()
			, ajaxObj = {};
		
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:9080/survey-application/api/surveyapp", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
				
			}, 
			dataType: "json"
		};
		
		$.ajax(ajaxObj);
	});
});