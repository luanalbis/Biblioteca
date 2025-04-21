/**
 * 
 */

function removerFotoPerfil() {
	const campoRemover = document.getElementById('removerFoto');
	if (campoRemover) {
		campoRemover.value = 'true';
		alert('A foto será removida ao salvar.');
	}
}


document.addEventListener('DOMContentLoaded', function() {
	const inputFoto = document.getElementById('inputFotoPerfil');
	const imagemPreview = document.getElementById('previewFoto');

	inputFoto.addEventListener('change', function() {
		if (inputFoto.files && inputFoto.files[0]) {
			const leitor = new FileReader();
			leitor.onload = function(e) {
				imagemPreview.src = e.target.result;
				document.getElementById('removerFoto').value = "false";
			};
			leitor.readAsDataURL(inputFoto.files[0]);
		}
	});
});


function removerFotoPerfil() {
	document.getElementById('removerFoto').value = "true";
	document.getElementById('previewFoto').src = 'imagens/user.png';
	document.getElementById('inputFotoPerfil').value = ''; 
}
