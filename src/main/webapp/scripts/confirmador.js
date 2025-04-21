/**
 * 
 */

function confirmar(idcadastro){
	let resposta = confirm("Confirma a exclusão do livro")
	if (resposta === true){
		window.location.href = "delete?idcadastro="+ idcadastro;
	}
}