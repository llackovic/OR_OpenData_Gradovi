	
$(document).ready(function() {
	
	// Setup - add a text input to each footer cell
    $('#citiesTable tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+ title +'" />' );
    } );
	
    $('#citiesTable').DataTable({
		
		initComplete: function () {
            // Apply the search
            this.api().columns().every( function () {
                var that = this;
 
                $( 'input', this.footer() ).on( 'keyup change clear', function () {
                    if ( that.search() !== this.value ) {
                        that
                            .search( this.value )
                            .draw();
                    }
                } );
            } );
        },

		"scrollX" : true
		
	});

	$('#citiesTable').each(function () {
        var $table = $(this);

        var $button = $("<button type='button'>");
        $button.text("Preuzmi CSV");
        $button.insertAfter($table);

        $button.click(function () {
            var csv = $table.first().table2csv({
                delivery: 'value'
            });
            window.location.href = 'data:text/csv;charset=UTF-8,' 
            + encodeURIComponent(csv);
        });
    });
			
	
});