$(document).ready(function() {
    $('#custListTable').DataTable({ "responsive": true, "pageLength": 5, "lengthMenu": [5, 10, 15, 25, 50] });
    
    $('#custListTable tbody').on('click', 'tr', function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            $('#custListTable').DataTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        $('#modal-iframe').iziModal('open');
    });
    
    $("#modal-iframe").iziModal({
        title: 'Customer Portfolio',
        subtitle: 'Personal Particulars, Transactions, Recent History',
        iconClass: 'fa fa-user-circle',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 800,
        overlayClose: true,
        iframe : true,
        iframeURL: 'SGMapleStore?pageTransit=goToCustomerDetails',
        iframeHeight: 455
    });
});