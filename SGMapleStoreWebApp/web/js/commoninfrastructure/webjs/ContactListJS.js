$(document).ready(function() {
    $('#contactList tbody').on('click', 'tr', function() {
        $('#modal-iframe').iziModal('open');
    });
    
    $("#modal-iframe").iziModal({
        title: 'Contact Portfolio',
        subtitle: 'Contact Particulars, Transactions, Recent History',
        iconClass: 'fa fa-user-circle',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 800,
        overlayClose: true,
        iframe : true,
        iframeURL: 'SGMapleStore?pageTransit=goToContactDetails',
        iframeHeight: 455
    });
});