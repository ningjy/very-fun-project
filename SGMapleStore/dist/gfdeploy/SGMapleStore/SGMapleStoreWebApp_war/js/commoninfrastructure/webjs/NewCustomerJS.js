$(document).ready(function() {
    $('.nav-tabs > li > a').click(function(e) {
        e.preventDefault();
        var active_tab_selector = $('.nav-tabs > li.active > a').attr('href');
        
        var actived_nav = $('.nav-tabs > li.active');
        actived_nav.removeClass('active');

        /* ADD 'active' CSS INTO CLICKED NAVIGATION */
        $(this).parents('li').addClass('active');

        /* HIDE DISPLAYING TAB CONTENT */
        $(active_tab_selector).removeClass('active');
        $(active_tab_selector).addClass('hide');

        /* SHOW TARGET TAB CONTENT */
        var target_tab_selector = $(this).attr('href');
        $(target_tab_selector).removeClass('hide');
        $(target_tab_selector).addClass('active');
    });
});

function copyBillingAdd() {
    $('#custShippingStreet').val($('#custBillingStreet').val());
    $('#custShippingCity').val($('#custBillingCity').val());
    $('#custShippingState').val($('#custBillingState').val());
    $('#custShippingZipCode').val($('#custBillingZipCode').val());
    $('#custShippingCountry').val($('#custBillingCountry').val());
}