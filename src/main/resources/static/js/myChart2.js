var chartDataStr = decodeHtml(barchartdata);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for (var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myBarChart"), {
	type : 'bar',
	// The data for our dataset
	data : {
		labels : labelData,
		datasets : [ {
			minBarThickness : 20,
			maxBarThickness : 80,
			label : [ 'User Activation Bar-Chart' ],
			//            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
			//            backgroundColor: ["orange", "green"],
			backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)' ],
			borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)' ],
			borderWidth : 2,

			data : numericData
		} ]
	},

	// Configuration options go here
	options : {
		maintainAspectRatio : false,
		responsive : true,
		scales : {
			xAxes : [ {

				ticks : {
					maxRotation : 90,
					minRotation : 45
				}
			} ],
			yAxes : [ {
				ticks : {
					beginAtZero : true
				}
			} ]
		}
	}
});

// "[{"value": 1, "label": "COMPLETED"},{"value": 2, "label": "INPROGRESS"},{"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}