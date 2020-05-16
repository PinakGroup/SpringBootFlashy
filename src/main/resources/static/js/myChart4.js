var chartDataStr = decodeHtml(barchartdata3);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for (var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myBarChart3"), {
	type : 'bar',

	// The data for our dataset
	data : {
		labels : labelData,
		datasets : [ {
			minBarThickness : 10,
			maxBarThickness : 40,
			label : [ 'AppUser Role Count' ],
			//            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
			//            backgroundColor: ["orange", "green"],
			backgroundColor : "rgba(255, 99, 132, 0.2)",
			borderColor : "rgba(255, 99, 132, 1)",
			hoverRadius : 500,
			intersect : true,
			hoverBackgroundColor : 'red',
			hoverBorderColor : 'white',
			hoverBorderWidth : '4',
			borderWidth : 2,
			//                precision : 1,
			//                stepSize:1,

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
					beginAtZero : true,
					// stepSize: 0.5
					stepSize : 1
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