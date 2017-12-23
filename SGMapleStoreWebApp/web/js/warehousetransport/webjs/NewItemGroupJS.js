/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#profileImage')
                .attr('src', e.target.result)
                .css({'height': '115px', 'width': '145px'});
        };
        reader.readAsDataURL(input.files[0]);
    }
}