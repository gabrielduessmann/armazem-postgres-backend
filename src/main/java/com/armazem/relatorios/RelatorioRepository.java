package com.armazem.relatorios;

import com.armazem.relatorios.dto.RelatorioProdutosArmazenadosListagemDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface RelatorioRepository {
    @Query(
            value = "SELECT g.nome AS nomegalpao, es.setor AS setorestoque, p.nome AS nomeproduto, ar.quantidade, ar.ultimaatualizacao \n" +
                    "FROM alocacao a \n" +
                    "JOIN empresa e ON a.empresa_id = e.empresa_id\n" +
                    "JOIN estoque es ON a.estoque_id = es.estoque_id \n" +
                    "JOIN galpao g ON es.galpao_galpao_id = g.galpao_id\n" +
                    "JOIN armazenamento ar ON es.estoque_id = ar.estoque_id\n" +
                    "JOIN produto p ON ar.produto_id = p.produto_id\n" +
                    "WHERE e.empresa_id = :empresaId;",
            nativeQuery = true)
    public ArrayList<Tuple> listarProdutosArmazenados(@Param("empresaId") UUID empresaId);
}
