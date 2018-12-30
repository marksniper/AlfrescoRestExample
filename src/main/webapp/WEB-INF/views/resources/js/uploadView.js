$(document).on('click', '#uploadFile ', function (e)
{
    $('#checkPostOperationForUpload').val("no");
    e.preventDefault();
    //Disable submit button
    $(this).prop('disabled', true);
    fileInInput = document.getElementById('inputFileInput');

    var formData = new FormData();
    formData.append('file', $("#inputFileInput")[0].files[0]);
    formData.append('idCartella',$('#idNodoPerAltreOperazioni').val() );
    var ajaxReq = $.ajax
    ({
        url: 'http://localhost:8010/node/fileUpload',
        type: 'POST',
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        xhr: function () {
            //Get XmlHttpRequest object
            var xhr = $.ajaxSettings.xhr();

            //Set onprogress event handler
            xhr.upload.onprogress = function (event) {
                var perc = Math.round((event.loaded / event.total) * 100);
                $('#progressBar').text(perc + '%');
                $('#progressBar').css('width', perc + '%');
            };
            return xhr;
        },
        beforeSend: function (xhr) {
            //Reset alert message and progress bar
           /* xhr.setRequestHeader('idCartella', '' + $('#idNodoPerAltreOperazioni').val());*/
            $('#alertMsg').text('');
            $('#progressBar').text('');
            $('#progressBar').css('width', '0%');
        }
    });

    // Called on success of file upload
    ajaxReq.done(function (msg) {
        //$('#alertMsg').text(msg);
        $('#alertMsg').text("Il file è stato caricato con successo!");
        $('input[type=file]').val('');
        $('button[type=submit]').prop('disabled', false);
        $('#checkPostOperationForUpload').val("yes");

    });

    // Called on failure of file upload
    ajaxReq.fail(function (jqXHR) {
        $('#alertMsg').text("Errore nel caricare il file!");
        $('button[type=submit]').prop('disabled', false);

    });
});