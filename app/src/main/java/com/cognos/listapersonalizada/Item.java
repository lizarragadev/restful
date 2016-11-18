package com.cognos.listapersonalizada;

import android.graphics.drawable.Drawable;

/**
 * Created by ANDROID on 7/11/2016.
 */

public class Item {
    protected Drawable imagen;
    protected String titulo;
    protected String subTitulo;
    protected long id;

    public Item(Drawable imagen, String titulo, String subTitulo, long id) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.id = id;
    }

    public Item(Drawable imagen, String titulo, String subTitulo) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }
}
