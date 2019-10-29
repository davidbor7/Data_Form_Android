package com.example.datospersonales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//Prueba
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText texto_nombre = (EditText) findViewById(R.id.campo_nombre);
        final EditText texto_apellidos = (EditText) findViewById(R.id.campo_apellidos);
        final EditText texto_edad = (EditText) findViewById(R.id.campo_edad);
        final TextView area_de_texto = (TextView) findViewById(R.id.area_de_texto);

        final Switch switch_hijos = (Switch) findViewById(R.id.switch1);
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);
        final Button btn_aceptar = (Button) findViewById(R.id.btn_aceptar);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_grupo);

        final Resources recursos = getResources();
        final String[] datos = recursos.getStringArray(R.array.arrayEstados);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        final Spinner cmbOpciones = (Spinner) findViewById(R.id.spinner_estado_civil);

        cmbOpciones.setAdapter(adaptador);

        //ANONIMA BOTON 1

        btn_reset.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                texto_nombre.setText("");
                texto_apellidos.setText("");
                texto_edad.setText("");
                radioGroup.clearCheck();
                switch_hijos.setChecked(false);
                cmbOpciones.setSelection(0);
                area_de_texto.setText("");
                texto_nombre. requestFocus();

            }
        });

        btn_aceptar.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (texto_nombre.getText().toString().isEmpty() == true) {

                    area_de_texto.setText("Rellena el campo Nombre");
                    texto_nombre.requestFocus();


                } else {

                    if (texto_apellidos.getText().toString().isEmpty() == true) {

                        area_de_texto.setText("Rellena el campo Apellidos");
                        texto_apellidos.requestFocus();

                    } else {

                        if (texto_edad.getText().toString().isEmpty() == true) {

                            area_de_texto.setText("Rellena el campo Edad");
                            texto_edad.requestFocus();

                        } else {
                            if (radioGroup.getCheckedRadioButtonId() == -1) {

                                area_de_texto.setText("Selecciona un género");
                                radioGroup.requestFocus();

                            }else {

                                if(cmbOpciones.getSelectedItemPosition()==0) {

                                    area_de_texto.setText("Selecciona un estado civil");
                                    cmbOpciones.requestFocus();
                                   }
                                    else
                                    {
                                        area_de_texto.setTextColor(Color.BLACK);

                                        area_de_texto.setText(texto_apellidos.getText().toString()+ ", " + texto_nombre.getText().toString());

                                        String string_edad = texto_edad.getText().toString();
                                        int entero_edad = Integer.parseInt(string_edad);

                                        //------------------ //COMPRUEBA SI ES MENOR DE EDAD--------------------

                                        if(entero_edad>=18)
                                        {
                                            area_de_texto.append(", "+ "mayor de edad, ");

                                        }else{

                                            area_de_texto.append(", "+ "menor de edad, ");

                                        }
                                        //----------------------------------------------------------------------


                                        //------------------ //COMPRUEBA EL GÉNERO-----------------------------

                                        int radioButtonId = radioGroup.getCheckedRadioButtonId();
                                        View radioButton = radioGroup.findViewById(radioButtonId);
                                        int indice = radioGroup.indexOfChild(radioButton);
                                        RadioButton btn = (RadioButton) radioGroup.getChildAt(indice);
                                        String selection = btn.getText().toString().toLowerCase();

                                        area_de_texto.append(selection + " ");


                                        //----------------------------------------------------------------------


                                        //------------------ //COMPRUEBA EL ESTADO CIVIL-----------------------------


                                        String texto_estado_civil = cmbOpciones.getSelectedItem().toString().toLowerCase();
                                        area_de_texto.append(texto_estado_civil + " y ");

                                        //----------------------------------------------------------------------

                                        //------------------ //COMPRUEBA HIJOS--------------------------

                                        if(switch_hijos.isChecked())

                                            area_de_texto.append("con hijos.");

                                        else{
                                            area_de_texto.append("sin hijos.");
                                        }

                                        //----------------------------------------------------------------------



                                    }
                                }
                            }
                        }
                    }
                }
        });
                }
            }



