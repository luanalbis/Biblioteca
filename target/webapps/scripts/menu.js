/**
 * 
 */

const menuToggle = document.getElementById("btnMenuMobile");
const menuDropdown = document.getElementById("menuDropdown");

menuToggle.addEventListener("click", () => {
  menuDropdown.classList.toggle("active");
});
