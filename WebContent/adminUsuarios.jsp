<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, javax.servlet.*, javax.servlet.http.*, java.sql.*,modelo.*,java.io.File"%>
<!DOCTYPE html>
<html>
	<head>
		<title>OGGenius IV | Gestion de comentarios</title>
			<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
	<link rel="stylesheet" href="../../plugins/datatables/dataTables.bootstrap.css">
	<script>
	function migo(cod_comen) {
		$.ajax({
			url : '/OGgenius/GestionUsuarios?del='+cod_comen,
			type : 'get',
			dataType : 'html', //expect return data as html from server
			data : '',
			success : function(response, textStatus, jqXHR) {
				$('#lista').html(response);
				return false;//select the id and put the response in the html
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error(s):' + textStatus, errorThrown);
			}
		});
		return false;
	}
	</script>
	</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
				<header class="main-header">
		<a href="index2.html" class="logo">
			<span class="logo-mini"><b>CP</b>3</span>
			<span class="logo-lg"><b>CPDin</b>3</span>
		</a>
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown messages-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-envelope-o"></i>
							<span class="label label-success">4</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">You have 4 messages</li>
							<li>
								<ul class="menu">
									<li>
										<a href="#">
											<div class="pull-left">
												<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
											</div>
											<h4>Support Team<small><i class="fa fa-clock-o"></i> 5 mins</small></h4>
											<p>Why not buy a new awesome theme?</p>
										</a>
									</li>
								</ul>
							</li>
							<li class="footer"><a href="#">See All Messages</a></li>
						</ul>
					</li>
					<li class="dropdown notifications-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-bell-o"></i>
							<span class="label label-warning">10</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">You have 10 notifications</li>
							<li>
								<ul class="menu">
									<li>
										<a href="#">
											<i class="fa fa-users text-aqua"></i> 5 new members joined today
										</a>
									</li>
								</ul>
							</li>
							<li class="footer"><a href="#">View all</a></li>
						</ul>
					</li>
					<li class="dropdown tasks-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-flag-o"></i>
							<span class="label label-danger">9</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">You have 9 tasks</li>
							<li>
								<ul class="menu">
									<li>
										<a href="#">
											<h3>Design some buttons<small class="pull-right">20%</small></h3>
											<div class="progress xs">
												<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
													<span class="sr-only">20% Complete</span>
												</div>
											</div>
										</a>
									</li>
								</ul>
							</li>
							<li class="footer">
								<a href="#">View all tasks</a>
							</li>
						</ul>
					</li>
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="hidden-xs">		
<%= session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos") %>
</span>
						</a>
						<ul class="dropdown-menu">
							<li class="user-header">
								<p>
											
<%= session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos") %>
								</p>
							</li>
							<li class="user-footer">
								<div class="pull-right">
									<a href="logout" class="btn btn-default btn-flat">Logout</a>
								</div>
							</li>
						</ul>
					</li>
					<li>
						<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
				<aside class="main-sidebar">
		<section class="sidebar">
<%
	String rutaImagen = "perfiles/default.jpg";
	String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
	File tieneImagen = new File(directorio + session.getAttribute("cod_usr") + ".jpg");
	if (tieneImagen.exists()) {
		rutaImagen = "perfiles/" + session.getAttribute("cod_usr") + ".jpg";
	}
%>

			<div class="user-panel">
				<div class="pull-left image">
					<img src="<%= rutaImagen %>" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info" heigth="160">
					<p>		
<%= session.getAttribute("nombre") + "\t" + session.getAttribute("apellidos") %>
</p>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">HEADER</li>
				            
						<li><a href="/petiadmin"><i class="fa fa-link"></i> <span>Gestión de Usuarios</span></a></li>
						<li><a href="/eventviewer"><i class="fa fa-link"></i> <span>Gestion de canciones</span></a></li>
						<li class="active"><a href="/userviewer"><i class="fa fa-link"></i> <span>Gestion de Artistas</span></a></li>
						<li><a href="/showenterprise"><i class="fa fa-link"></i> <span>Gestión de temas</span></a></li>
						<li><a href="/logviewer"><i class="fa fa-link"></i> <span>Gestion de momentos</span></a></li>
			</ul>
		</section>
	</aside>
				<div class="content-wrapper">
		<section class="content-header">
			<div id="lista">
	<table id="users">
		<tr>
			<th>cod_usr</th>
			<th>nombre</th>
			<th>Apellidos</th>
			<th>mail</th>
			<th>Contrase&ntilde;a</th>
			<th>Activo</th>
			<th>tema</th>
			<th>modificar</th>
			<th>borrar</th>
		</tr>
		<%
			Object users = request.getAttribute("usuarios"); 
			ArrayList <Usuario> usuarios = (ArrayList<Usuario>) users;
			Object oretomba = request.getAttribute("errores");
			ArrayList<String> errores = (ArrayList<String>) oretomba;
			if (errores != null) {
				if (errores.size() != 0) {
		%>
		<h2>Lista de errores</h2>
		<%
			for (int i = 0; i < errores.size(); i++) {
		%>
		<p><%=errores.get(i)%></p>
		<%
			}
				}
			}
			for(int i=0;i<usuarios.size();i++) {
		%>
		<tr>
			<td><%= usuarios.get(i).getCod_usr() %></td>
			<td><%= usuarios.get(i).getNombre() %></td>
			<td><%= usuarios.get(i).getApellidos() %></td>
			<td><%= usuarios.get(i).getMail() %></td>
			<td><%= usuarios.get(i).getActivo() %></td>
			<td><%= usuarios.get(i).getTema() %></td>
			<td><input type="image" src="recursos/hoja.jpg" alt="modificar"/></td>
			<td><input type="image" src="recursos/aspa.jpg" alt="borrar" onclick="migo(<%= usuarios.get(i).getCod_usr() %>)"/></td>
		</tr>
		<%
			}
		%>
	</table>
					<center>
						<input type="image" src="recursos/anadir.jpg" onclick="window.open('/OGgenius/GestionUsuarios?frame=1','_self')"/>
					</center>
				</div>
		</section>
	</div>
	<div class="control-sidebar-bg"></div>
			 	<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <script src="dist/js/app.min.js"></script>
    <script src="dist/js/demo.js"></script>
			>
<script>
	$("#users").DataTable();
</script>
		</div>
	
<div id="sfwdtfac80d" class="sf-toolbar" style="display: none"></div><script>/*<![CDATA[*/        Sfjs = (function() {        "use strict";        var classListIsSupported = 'classList' in document.documentElement;        if (classListIsSupported) {            var hasClass = function (el, cssClass) { return el.classList.contains(cssClass); };            var removeClass = function(el, cssClass) { el.classList.remove(cssClass); };            var addClass = function(el, cssClass) { el.classList.add(cssClass); };            var toggleClass = function(el, cssClass) { el.classList.toggle(cssClass); };        } else {            var hasClass = function (el, cssClass) { return el.className.match(new RegExp('\\b' + cssClass + '\\b')); };            var removeClass = function(el, cssClass) { el.className = el.className.replace(new RegExp('\\b' + cssClass + '\\b'), ' '); };            var addClass = function(el, cssClass) { if (!hasClass(el, cssClass)) { el.className += " " + cssClass; } };            var toggleClass = function(el, cssClass) { hasClass(el, cssClass) ? removeClass(el, cssClass) : addClass(el, cssClass); };        }        var noop = function() {},            collectionToArray = function (collection) {                var length = collection.length || 0,                    results = new Array(length);                while (length--) {                    results[length] = collection[length];                }                return results;            },            profilerStorageKey = 'sf2/profiler/',            request = function(url, onSuccess, onError, payload, options) {                var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');                options = options || {};                options.maxTries = options.maxTries || 0;                xhr.open(options.method || 'GET', url, true);                xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');                xhr.onreadystatechange = function(state) {                    if (4 !== xhr.readyState) {                        return null;                    }                    if (xhr.status == 404 && options.maxTries > 1) {                        setTimeout(function(){                            options.maxTries--;                            request(url, onSuccess, onError, payload, options);                        }, 500);                        return null;                    }                    if (200 === xhr.status) {                        (onSuccess || noop)(xhr);                    } else {                        (onError || noop)(xhr);                    }                };                xhr.send(payload || '');            },            getPreference = function(name) {                if (!window.localStorage) {                    return null;                }                return localStorage.getItem(profilerStorageKey + name);            },            setPreference = function(name, value) {                if (!window.localStorage) {                    return null;                }                localStorage.setItem(profilerStorageKey + name, value);            },            requestStack = [],            renderAjaxRequests = function() {                var requestCounter = document.querySelectorAll('.sf-toolbar-ajax-requests');                if (!requestCounter.length) {                    return;                }                var ajaxToolbarPanel = document.querySelector('.sf-toolbar-block-ajax');                var tbodies = document.querySelectorAll('.sf-toolbar-ajax-request-list');                var state = 'ok';                if (tbodies.length) {                    var tbody = tbodies[0];                    var rows = document.createDocumentFragment();                    if (requestStack.length) {                        for (var i = 0; i < requestStack.length; i++) {                            var request = requestStack[i];                            var row = document.createElement('tr');                            rows.insertBefore(row, rows.firstChild);                            var methodCell = document.createElement('td');                            if (request.error) {                                methodCell.className = 'sf-ajax-request-error';                            }                            methodCell.textContent = request.method;                            row.appendChild(methodCell);                            var pathCell = document.createElement('td');                            pathCell.className = 'sf-ajax-request-url';                            if ('GET' === request.method) {                                var pathLink = document.createElement('a');                                pathLink.setAttribute('href', request.url);                                pathLink.textContent = request.url;                                pathCell.appendChild(pathLink);                            } else {                                pathCell.textContent = request.url;                            }                            pathCell.setAttribute('title', request.url);                            row.appendChild(pathCell);                            var durationCell = document.createElement('td');                            durationCell.className = 'sf-ajax-request-duration';                            if (request.duration) {                                durationCell.textContent = request.duration + "ms";                            } else {                                durationCell.textContent = '-';                            }                            row.appendChild(durationCell);                            row.appendChild(document.createTextNode(' '));                            var profilerCell = document.createElement('td');                            if (request.profilerUrl) {                                var profilerLink = document.createElement('a');                                profilerLink.setAttribute('href', request.profilerUrl);                                profilerLink.textContent = request.profile;                                profilerCell.appendChild(profilerLink);                            } else {                                profilerCell.textContent = 'n/a';                            }                            row.appendChild(profilerCell);                            var requestState = 'ok';                            if (request.error) {                                requestState = 'error';                                if (state != "loading" && i > requestStack.length - 4) {                                    state = 'error';                                }                            } else if (request.loading) {                                requestState = 'loading';                                state = 'loading';                            }                            row.className = 'sf-ajax-request sf-ajax-request-' + requestState;                        }                        var infoSpan = document.querySelectorAll(".sf-toolbar-ajax-info")[0];                        var children = collectionToArray(tbody.children);                        for (var i = 0; i < children.length; i++) {                            tbody.removeChild(children[i]);                        }                        tbody.appendChild(rows);                        if (infoSpan) {                            var text = requestStack.length + ' AJAX request' + (requestStack.length > 1 ? 's' : '');                            infoSpan.textContent = text;                        }                        ajaxToolbarPanel.style.display = 'block';                    } else {                        ajaxToolbarPanel.style.display = 'none';                    }                }                requestCounter[0].textContent = requestStack.length;                var className = 'sf-toolbar-ajax-requests sf-toolbar-value';                requestCounter[0].className = className;                if (state == 'ok') {                    Sfjs.removeClass(ajaxToolbarPanel, 'sf-ajax-request-loading');                    Sfjs.removeClass(ajaxToolbarPanel, 'sf-toolbar-status-red');                } else if (state == 'error') {                    Sfjs.addClass(ajaxToolbarPanel, 'sf-toolbar-status-red');                    Sfjs.removeClass(ajaxToolbarPanel, 'sf-ajax-request-loading');                } else {                    Sfjs.addClass(ajaxToolbarPanel, 'sf-ajax-request-loading');                }            };        var addEventListener;        var el = document.createElement('div');        if (!'addEventListener' in el) {            addEventListener = function (element, eventName, callback) {                element.attachEvent('on' + eventName, callback);            };        } else {            addEventListener = function (element, eventName, callback) {                element.addEventListener(eventName, callback, false);            };        }                    if (window.XMLHttpRequest && XMLHttpRequest.prototype.addEventListener) {                var proxied = XMLHttpRequest.prototype.open;                XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {                    var self = this;                    /* prevent logging AJAX calls to static and inline files, like templates */                    var path = url;                    if (url.substr(0, 1) === '/') {                        if (0 === url.indexOf('')) {                            path = url.substr(0);                        }                    }                    else if (0 === url.indexOf('http\x3A\x2F\x2F127.0.0.1\x3A8000')) {                        path = url.substr(21);                    }                    if (!path.match(new RegExp("^\/(app(_[\\w]+)?\\.php\/)?_wdt"))) {                        var stackElement = {                            loading: true,                            error: false,                            url: url,                            method: method,                            start: new Date()                        };                        requestStack.push(stackElement);                        this.addEventListener('readystatechange', function() {                            if (self.readyState == 4) {                                stackElement.duration = new Date() - stackElement.start;                                stackElement.loading = false;                                stackElement.error = self.status < 200 || self.status >= 400;                                stackElement.profile = self.getResponseHeader("X-Debug-Token");                                stackElement.profilerUrl = self.getResponseHeader("X-Debug-Token-Link");                                Sfjs.renderAjaxRequests();                            }                        }, false);                        Sfjs.renderAjaxRequests();                    }                    proxied.apply(this, Array.prototype.slice.call(arguments));                };            }                return {            hasClass: hasClass,            removeClass: removeClass,            addClass: addClass,            toggleClass: toggleClass,            getPreference: getPreference,            setPreference: setPreference,            addEventListener: addEventListener,            request: request,            renderAjaxRequests: renderAjaxRequests,            load: function(selector, url, onSuccess, onError, options) {                var el = document.getElementById(selector);                if (el && el.getAttribute('data-sfurl') !== url) {                    request(                        url,                        function(xhr) {                            el.innerHTML = xhr.responseText;                            el.setAttribute('data-sfurl', url);                            removeClass(el, 'loading');                            (onSuccess || noop)(xhr, el);                        },                        function(xhr) { (onError || noop)(xhr, el); },                        '',                        options                    );                }                return this;            },            toggle: function(selector, elOn, elOff) {                var tmp = elOn.style.display,                    el = document.getElementById(selector);                elOn.style.display = elOff.style.display;                elOff.style.display = tmp;                if (el) {                    el.style.display = 'none' === tmp ? 'none' : 'block';                }                return this;            },            createTabs: function() {                var tabGroups = document.querySelectorAll('.sf-tabs');                /* create the tab navigation for each group of tabs */                for (var i = 0; i < tabGroups.length; i++) {                    var tabs = tabGroups[i].querySelectorAll('.tab');                    var tabNavigation = document.createElement('ul');                    tabNavigation.className = 'tab-navigation';                    for (var j = 0; j < tabs.length; j++) {                        var tabId = 'tab-' + i + '-' + j;                        var tabTitle = tabs[j].querySelector('.tab-title').innerHTML;                        var tabNavigationItem = document.createElement('li');                        tabNavigationItem.setAttribute('data-tab-id', tabId);                        if (j == 0) { Sfjs.addClass(tabNavigationItem, 'active'); }                        if (Sfjs.hasClass(tabs[j], 'disabled')) { Sfjs.addClass(tabNavigationItem, 'disabled'); }                        tabNavigationItem.innerHTML = tabTitle;                        tabNavigation.appendChild(tabNavigationItem);                        var tabContent = tabs[j].querySelector('.tab-content');                        tabContent.parentElement.setAttribute('id', tabId);                    }                    tabGroups[i].insertBefore(tabNavigation, tabGroups[i].firstChild);                }                /* display the active tab and add the 'click' event listeners */                for (i = 0; i < tabGroups.length; i++) {                    tabNavigation = tabGroups[i].querySelectorAll('.tab-navigation li');                    for (j = 0; j < tabNavigation.length; j++) {                        tabId = tabNavigation[j].getAttribute('data-tab-id');                        document.getElementById(tabId).querySelector('.tab-title').className = 'hidden';                        if (Sfjs.hasClass(tabNavigation[j], 'active')) {                            document.getElementById(tabId).className = 'block';                        } else {                            document.getElementById(tabId).className = 'hidden';                        }                        tabNavigation[j].addEventListener('click', function(e) {                            var activeTab = e.target || e.srcElement;                            /* needed because when the tab contains HTML contents, user can click */                            /* on any of those elements instead of their parent '<li>' element */                            while (activeTab.tagName.toLowerCase() !== 'li') {                                activeTab = activeTab.parentNode;                            }                            /* get the full list of tabs through the parent of the active tab element */                            var tabNavigation = activeTab.parentNode.children;                            for (var k = 0; k < tabNavigation.length; k++) {                                var tabId = tabNavigation[k].getAttribute('data-tab-id');                                document.getElementById(tabId).className = 'hidden';                                Sfjs.removeClass(tabNavigation[k], 'active');                            }                            Sfjs.addClass(activeTab, 'active');                            var activeTabId = activeTab.getAttribute('data-tab-id');                            document.getElementById(activeTabId).className = 'block';                        });                    }                }            },            createToggles: function() {                var toggles = document.querySelectorAll('.sf-toggle');                for (var i = 0; i < toggles.length; i++) {                    var elementSelector = toggles[i].getAttribute('data-toggle-selector');                    var element = document.querySelector(elementSelector);                    Sfjs.addClass(element, 'sf-toggle-content');                    if (toggles[i].hasAttribute('data-toggle-initial') && toggles[i].getAttribute('data-toggle-initial') == 'display') {                        Sfjs.addClass(element, 'sf-toggle-visible');                    } else {                        Sfjs.addClass(element, 'sf-toggle-hidden');                    }                    Sfjs.addEventListener(toggles[i], 'click', function(e) {                        e.preventDefault();                        var toggle = e.target || e.srcElement;                        /* needed because when the toggle contains HTML contents, user can click */                        /* on any of those elements instead of their parent '.sf-toggle' element */                        while (!Sfjs.hasClass(toggle, 'sf-toggle')) {                            toggle = toggle.parentNode;                        }                        var element = document.querySelector(toggle.getAttribute('data-toggle-selector'));                        Sfjs.toggleClass(element, 'sf-toggle-hidden');                        Sfjs.toggleClass(element, 'sf-toggle-visible');                        /* the toggle doesn't change its contents when clicking on it */                        if (!toggle.hasAttribute('data-toggle-alt-content')) {                            return;                        }                        if (!toggle.hasAttribute('data-toggle-original-content')) {                            toggle.setAttribute('data-toggle-original-content', toggle.innerHTML);                        }                        var currentContent = toggle.innerHTML;                        var originalContent = toggle.getAttribute('data-toggle-original-content');                        var altContent = toggle.getAttribute('data-toggle-alt-content');                        toggle.innerHTML = currentContent !== altContent ? altContent : originalContent;                    });                }            }        };    })();    Sfjs.addEventListener(window, 'load', function() {        Sfjs.createTabs();        Sfjs.createToggles();    });/*]]>*/</script><script>/*<![CDATA[*/    (function () {                Sfjs.load(            'sfwdtfac80d',            '/_wdt/fac80d',            function(xhr, el) {                el.style.display = -1 !== xhr.responseText.indexOf('sf-toolbarreset') ? 'block' : 'none';                if (el.style.display == 'none') {                    return;                }                if (Sfjs.getPreference('toolbar/displayState') == 'none') {                    document.getElementById('sfToolbarMainContent-fac80d').style.display = 'none';                    document.getElementById('sfToolbarClearer-fac80d').style.display = 'none';                    document.getElementById('sfMiniToolbar-fac80d').style.display = 'block';                } else {                    document.getElementById('sfToolbarMainContent-fac80d').style.display = 'block';                    document.getElementById('sfToolbarClearer-fac80d').style.display = 'block';                    document.getElementById('sfMiniToolbar-fac80d').style.display = 'none';                }                Sfjs.renderAjaxRequests();                /* Handle toolbar-info position */                var toolbarBlocks = document.querySelectorAll('.sf-toolbar-block');                for (var i = 0; i < toolbarBlocks.length; i += 1) {                    toolbarBlocks[i].onmouseover = function () {                        var toolbarInfo = this.querySelectorAll('.sf-toolbar-info')[0];                        var pageWidth = document.body.clientWidth;                        var elementWidth = toolbarInfo.offsetWidth;                        var leftValue = (elementWidth + this.offsetLeft) - pageWidth;                        var rightValue = (elementWidth + (pageWidth - this.offsetLeft)) - pageWidth;                        /* Reset right and left value, useful on window resize */                        toolbarInfo.style.right = '';                        toolbarInfo.style.left = '';                        if (elementWidth > pageWidth) {                            toolbarInfo.style.left = 0;                        }                        else if (leftValue > 0 && rightValue > 0) {                            toolbarInfo.style.right = (rightValue * -1) + 'px';                        } else if (leftValue < 0) {                            toolbarInfo.style.left = 0;                        } else {                            toolbarInfo.style.right = '0px';                        }                    };                }            },            function(xhr) {                if (xhr.status !== 0) {                    confirm('An error occurred while loading the web debug toolbar (' + xhr.status + ': ' + xhr.statusText + ').\n\nDo you want to open the profiler?') && (window.location = '/_profiler/fac80d');                }            },            {'maxTries': 5}        );    })();/*]]>*/</script>
</body>
</html>