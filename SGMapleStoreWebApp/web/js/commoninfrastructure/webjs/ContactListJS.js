var rowEmail;
$(document).ready(function() {
    $('#contactList tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        rowEmail = $.trim(rowData[2]);
        $('iframe').attr('src', 'SGMapleStore?pageTransit=goToContactDetails&contactIdentifier=' + rowEmail);
        $('#modal-iframe').iziModal('open', event);
    });
    
    $("#modal-iframe").iziModal({
        title: 'Contact Portfolio',
        subtitle: 'Contact Particulars, Transactions, Recent History',
        iconClass: 'fa fa-user-circle',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 525
    });
});