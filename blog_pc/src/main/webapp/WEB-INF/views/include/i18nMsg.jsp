<script type="text/javascript" src="js/jquery.i18n.properties.min.js"></script>
<script type="text/javascript">
jQuery.i18n.properties({ name: 'messages', path: 'resources/i18n/', mode: 'map', language: 'zh', cache: false, encoding: 'UTF-8'});

function i18nMsg(key){
	return jQuery.i18n.prop(key);
}
</script>