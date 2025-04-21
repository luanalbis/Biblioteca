/**
 * 
 */

function validador() {
	let titulo = frmAdicionar.titulo.value
	let autor = frmAdicionar.autor.value
	let genero = frmAdicionar.genero.value
	let estado = frmAdicionar.estado.value


	if (titulo === "") {
		alert("Preencha o campo TÌTULO")
		frmAdicionar.titulo.focus();
		return false;
	} else if (autor === "") {
		alert("Preencha o campo AUTOR")
		frmAdicionar.autor.focus();
		return false;
	} else if (genero === "") {
		alert("Selecione o GÊNERO")
		frmAdicionar.genero.focus();
		return false;
	} else if (estado === "") {
		alert("Selecione o ESTADO")
		frmAdicionar.estado.focus();
		return false;
	} else {
		document.forms["frmAdicionar"].submit();
	}

}

function validarLogin() {
	let login = frmLogin.login.value.trim()
	let senha = frmLogin.senha.value.trim()

	if (login === "") {
		alert("Digite o LOGIN")
	} else if (senha === "") {
		alert("Digite a SENHA")
	} else {
		document.forms["frmLogin"].submit();
	}
}

function mostrarSenha(img) {
	const input = img.previousElementSibling;

	if (input.type === "password") {
		input.type = "text";
		img.src = "imagens/hide.png"; // imagem de "esconder"
		img.title = "Ocultar senha";
	} else {
		input.type = "password";
		img.src = "imagens/view.png"; // imagem de "mostrar"
		img.title = "Mostrar senha";
	}
}
function validarEditarPerfil() {
	const form = document.forms["frmEditarPerfil"];
	const nome = form.nome.value.trim();
	const email = form.email.value.trim();
	const senha = form.senha.value.trim();
	const senhaInput = form.senha;

	if (nome === "") {
		alert("Digite seu NOME");
		return false;
	} else if (email === "") {
		alert("Digite seu EMAIL");
		return false;
	} else if (!email.includes("@")) {
		alert("Digite um EMAIL válido");
		return false;
	
	} else if (senha.length < senhaInput.minLength) {
		alert(`A SENHA deve ter pelo menos ${senhaInput.minLength} caracteres`);
		return false;
	}

	form.submit();
	return true;
}



function atualizarSenha() {
	let form = document.forms["frmNovaSenha"];
	let senha1 = form.novaSenha1.value.trim();
	let senha2 = form.novaSenha2.value.trim();

	if (senha1 !== senha2) {
		alert("Senhas precisam ser iguais!");
		form.novaSenha2.focus();
		return false;
	}

	if (!form.checkValidity()) {
		form.reportValidity(); // Mostra as mensagens de erro do HTML5
		return false;
	}

	form.submit();
}