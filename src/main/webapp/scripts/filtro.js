/**
 * 
 */

// Função para alternar a visibilidade do container de filtros
document.addEventListener("DOMContentLoaded", function () {
    const btn = document.getElementById("btnFiltro");
    const container = document.getElementById("filtroContainer");

    btn.addEventListener("click", function () {
        container.style.display = container.style.display === "none" ? "block" : "none";
    });

    document.addEventListener("click", function (e) {
        if (!btn.contains(e.target) && !container.contains(e.target)) {
            container.style.display = "none";
        }
    });
});

