package com.arturopacheco.miscontactos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class registro extends AppCompatActivity {

    Button agregar;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ContentValues nuevoregistro=new ContentValues();

        agregar=(Button)findViewById(R.id.btnAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent agregar = new Intent(registro.this, ContactDataSource.class);
                startActivity(agregar);

            }

        });
    }

    public void createContact(final Contacto contact){
        agregar=(Button)findViewById(R.id.btnNuevocontacto);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put("nombre",contact.getNombre());
                values.put("telefono",contact.getTelefono());
                values.put("email",contact.getEmail());
                long id = database.insert("contact",null,values);
            }

        });


    }
    public ArrayList<Contacto> GetContactos(){
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        Cursor cursor = database.query("contact",new String[]{"id","nombre","telefono","email"},null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Contacto contact = cursorToContact(cursor);
            contactos.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contactos;

    }

    private Contacto cursorToContact (Cursor cursor){
        Contacto contact = new Contacto(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        contact.setId(cursor.getLong(0));

        return contact;
    }

}
