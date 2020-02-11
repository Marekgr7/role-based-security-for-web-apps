
app.directive('ngConfirmClick', [
    function(){
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.on('click',function (event) {
                    if ( window.confirm(msg) ) {
                        scope.$eval(clickAction);
                    }
                });
            }
        };
    }]);