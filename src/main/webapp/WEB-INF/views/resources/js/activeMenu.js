$(document).ready(function () {
    var nomePagina = document.location.pathname.match(/[^\/]+$/)[0];

    if (nomePagina == "semantic") {
        $('#1').attr('class', 'active item');
    }


});