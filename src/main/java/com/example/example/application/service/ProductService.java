package com.example.example.application.service;

import com.example.example.api.request.AddProductRequestDTO;
import com.example.example.api.request.UpdateProductRequestDTO;
import com.example.example.api.response.ProductJoinUserResponseDTO;
import com.example.example.api.response.ProductResponseDTO;
import com.example.example.domain.product.entity.Product;
import com.example.example.domain.product.repository.ProductCustomRepository;
import com.example.example.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository pr;
    private final ProductCustomRepository pcr;
    
    @Transactional
    public ProductResponseDTO addProduct(AddProductRequestDTO req){
        pr.findByName(req.name())
                .ifPresent(existingProduct -> {
                    throw new IllegalArgumentException("해당하는 이름의 제품이 이미 존재합니다.!");
                });
        Product product = req.toEntity();
        return pr.save(product).toDto();
    }

    @Transactional
    public ProductResponseDTO deleteProduct(Long id){
        Product product = pr.findById(id)
                .orElseThrow(()->new NotFoundException("해당하는 id의 제품을 찾을 수 없습니다!"));
        ProductResponseDTO res = product.toDto();
        pr.delete(product);
        return res;
    }


    @Transactional
    public ProductResponseDTO updateProduct(UpdateProductRequestDTO req) {
        Product product = pr.findById(req.id())
                .orElseThrow(() -> new NotFoundException("해당하는 id의 제품을 찾을 수 없습니다!"));
        return pr.save(product.update(req.name(),req.amount())).toDto();
    }
    
    public List<ProductResponseDTO> getProductList(){
        List<ProductResponseDTO> productList = new ArrayList<>();

        pr.findAll().forEach(product -> productList.add(product.toDto()));

        return productList;
    }

    public ProductResponseDTO getProduct(Long id) {
        Product product = pr.findById(id).orElseThrow(()->new IllegalArgumentException("해당 제품이 없습니다.."));
        return product.toDto();
    }


    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        return pr.findAll(pageable).map(Product::toDto);
    }

    public List<ProductResponseDTO> findByQdsl(Pageable pageable, int amount) {
        List<ProductResponseDTO> productList = new ArrayList<>();
        pcr.findAllProductsOrderByDescWhereAmountisDistinct(pageable, amount)
                .forEach(product -> productList.add(product.toDto()));
        return productList;
    }

    public List<ProductJoinUserResponseDTO> findProductJoinUserByQdsl(Pageable pageable, String email){
        List<ProductJoinUserResponseDTO> productList = new ArrayList<>();
        pcr.findAllProductsJoinUser(pageable, email).forEach(product -> productList.add(product.toJoinUserDto()));
        return productList;
    }
}
