package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta) {
        return String.format("Requisição respondida pela instância executando na porta %s", porta);
    }

    @GetMapping
    public ResponseEntity<Page<PagamentoDto>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var pagamentoDto = service.obterTodos(paginacao);
        return ResponseEntity.ok(pagamentoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> detalhar(@PathVariable @NotNull Long id) {
        PagamentoDto dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto,
                                                  UriComponentsBuilder uriBuilder) {
        PagamentoDto pagamentoDto = service.criarPagamento(dto);

        URI endereco = uriBuilder.path("pagamentos/{id}").buildAndExpand(pagamentoDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizar(@PathVariable @NotNull Long id,
                                                  @RequestBody @Valid PagamentoDto dto) {
        PagamentoDto atualizado = service.atualizarPagamento(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> remover(@PathVariable @NotNull Long id) {
        service.excluirPagamento(id);

        return ResponseEntity.noContent().build();
    }

}
