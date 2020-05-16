$(document).ready(function () {
  $.validator.setDefaults({
    submitHandler: function () {
      alert( "Form successful submitted!" );
    }
  });
  $('#newTaskModal').validate({
    rules: {
    	taskunitnumber: {
        required: true,
        digits: true,
      
    },
    messages: {
    	taskunitnumber: {
        required: "Please enter a task unit number",
        digits: "Please enter numbers only"
      },
    errorElement: 'span',
    errorPlacement: function (error, element) {
      error.addClass('invalid-feedback');
      element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass('is-invalid');
    }
    }
    }
    
});
});
