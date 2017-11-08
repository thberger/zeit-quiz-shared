// A javascript-enhanced crossword puzzle [c] Jesse Weisbeck, MIT/GPL 
(function($) {
	$(function() {

        $.getJSON( "/quizdata", function( data ) {
            $('#puzzle-wrapper').crossword(data.puzzledata);
        });

	})
	
})(jQuery);
