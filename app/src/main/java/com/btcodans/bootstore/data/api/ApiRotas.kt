package com.btcodans.bootstore.data.api

import com.btcodans.bootstore.data.models.CarrinhosResponse
import com.btcodans.bootstore.data.models.Postagem
import com.btcodans.bootstore.data.models.PostagensResponse
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.data.models.ProdutoDestaqueRespose
import com.btcodans.bootstore.data.models.ProdutosResponse
import com.btcodans.bootstore.data.models.Usuario
import com.btcodans.bootstore.data.models.UsuariosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRotas {

    // ---------- PRODUTOS ----------
    @GET("products")
    suspend fun listarProdutos(): ProdutosResponse

    @GET("products/{id}")
    suspend fun buscarProduto(@Path("id") id: Int): Produto


    // ---------- CARRINHOS ----------
    @GET("carts")
    suspend fun listarCarrinhos(): Response<CarrinhosResponse> // Usando Response para tratar erros HTTP

    @GET("carts/{id}")
    suspend fun buscarCarrinho(@Path("id") id: Int): Response<CarrinhosResponse>


    // ---------- USUÁRIOS ----------
    @GET("users")
    suspend fun listarUsuarios(): UsuariosResponse

    @GET("users/{id}")
    suspend fun buscarUsuario(@Path("id") id: Int): Usuario


    // ---------- POSTAGENS ----------
    @GET("posts")
    suspend fun listarPostagens(): PostagensResponse

    @GET("posts/{id}")
    suspend fun buscarPostagem(@Path("id") id: Int): Postagem


    // ---------- PRODUTOS DESTAQUE----------
    @GET("products?sortBy=title&order=asc")
    suspend fun produtosDestaques(): ProdutoDestaqueRespose

}
