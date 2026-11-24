package com.exemplo.notasia.controller;

import com.exemplo.notasia.model.Nota;
import com.exemplo.notasia.repository.NotaRepository;
import com.exemplo.notasia.service.IaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NotaController {

    private final NotaRepository repository;
    private final IaService ia;

    public NotaController(NotaRepository repository, IaService ia) {
        this.repository = repository;
        this.ia = ia;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("notas", repository.findAll());
        model.addAttribute("nota", new Nota());
        return "index";
    }

    @PostMapping("/notas")
    public String salvar(@ModelAttribute Nota nota, RedirectAttributes redirect) {

        if (nota.getConteudo() != null &&
                nota.getConteudo().length() > 10000) {

            redirect.addFlashAttribute(
                    "erro",
                    "O texto é muito grande, limite de 10000 caracteres"
            );

            return "redirect:/";
        }

        repository.save(nota);
        return "redirect:/";
    }

    @PostMapping("/notas/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/notas/{id}/resumir")
    public String resumir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            Nota nota = repository.findById(id).orElseThrow();
            nota.setResumo(ia.resumir(nota.getConteudo()));
            repository.save(nota);
        } catch (Exception e) {
            redirect.addFlashAttribute("erro", "Erro ao chamar a IA: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/notas/{id}/perguntar")
    public String perguntar(@PathVariable Long id,
                            @RequestParam String pergunta,
                            RedirectAttributes redirect) {

        if (pergunta == null || !pergunta.matches(".*[\\p{L}\\p{N}].*")) {
            redirect.addFlashAttribute(
                    "erro",
                    "Digite uma pergunta válida. Não utilize apenas símbolos ou caracteres especiais."
            );

            redirect.addFlashAttribute("idResposta", id);

            return "redirect:/";
        }

        try {
            Nota nota = repository.findById(id).orElseThrow();

            redirect.addFlashAttribute("idResposta", id);
            redirect.addFlashAttribute("pergunta", pergunta);

            redirect.addFlashAttribute(
                    "resposta",
                    ia.perguntar(nota.getConteudo(), pergunta)
            );

        } catch (Exception e) {
            redirect.addFlashAttribute(
                    "erro",
                    "Não foi possível obter uma resposta da IA. Tente novamente."
            );
        }

        return "redirect:/";
    }

}
