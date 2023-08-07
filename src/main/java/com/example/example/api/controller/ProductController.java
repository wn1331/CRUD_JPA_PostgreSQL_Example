package com.example.example.api.controller;

import com.example.example.api.request.AddProductRequestDTO;
import com.example.example.api.request.UpdateProductRequestDTO;
import com.example.example.api.response.ProductResponseDTO;
import com.example.example.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService ps;

    @Operation(summary = "상품 추가 요청", description = "상품 정보가 추가됩니다.", tags = {"ProductController"})
    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody AddProductRequestDTO dto) {
        return ResponseEntity.ok(ps.addProduct(dto));
    }
    @Operation(summary = "상품 제거 요청", description = "상품 정보가 제거됩니다.", tags = {"ProductController"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(ps.deleteProduct(id));
    }
    @Operation(summary = "상품 수정 요청", description = "상품 정보가 수정됩니다.", tags = {"ProductController"})
    @PatchMapping("/patch")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody UpdateProductRequestDTO dto){
        return ResponseEntity.ok(ps.updateProduct(dto));
    }
    @Operation(summary = "전체 상품 조회 요청", description = "상품 정보를 전부 조회합니다.", tags = {"ProductController"})
    @GetMapping("/getall")
    public ResponseEntity<List<ProductResponseDTO>> getProductList(){
        return ResponseEntity.ok(ps.getProductList());
    }
    @Operation(summary = "상품 조회 요청", description = "상품 정보를 조회합니다.", tags = {"ProductController"})
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(ps.getProduct(id));
    }
    /**
     * @URL http://localhost:8085/paging?page=0....
     * @param pageable
     * @return
     */
    @Operation(summary = "상품 조회 요청 by Paging", description = "상품 정보를 페이징하여 조회합니다.", tags = {"ProductController"})
    @GetMapping("/paging")
    public Page<ProductResponseDTO> find(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable pageable){
        return ps.findAll(pageable);
    }


    @GetMapping("/ex/{amount}")
    public List<ProductResponseDTO> ex(@PathVariable int amount){
        return ps.findByQdsl(amount);
    }

}
