package es.myparte.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import es.myparte.database.ParteDbAdapter;


/**
 * Created by oscarlozanohernaiz.
 */
public class Parte {
    private Context context;

    private long id;
    private long idAnterior;
    private String par_fecha;
    private String par_direccion;
    private String par_ciudad;
    private String par_codigoPostal;
    private String par_pais;
    private String par_atestado;
    private String par_herido;
    private String par_distintosAB;
    private String par_objetosA;
    private String par_testigos;
    private String par_nombreTestigo;
    private String par_telefonoTestigo;
    private String par_latitud;
    private String par_longitud;

    private String par_foto1;
    private String par_foto2;
    private String par_foto3;

    private String par_aseNombreA;
    private String par_aseEmailA;
    private String par_aseTelefonoA;
    private String par_vehMatriculaA;
    private String par_vehMarcaA;
    private String par_vehModeloA;
    private String par_vehRemolqueA;
    private String par_vehMatRemolA;
    private String par_segNombreA;
    private String par_segEmailA;
    private String par_segTelefonoA;
    private String par_conNombreA;
    private String par_conEmailA;
    private String par_conTelefonoA;
    private String par_danosA;
    private String par_circunstanciasA;

    private String par_aseNombreB;
    private String par_aseEmailB;
    private String par_aseTelefonoB;
    private String par_vehMatriculaB;
    private String par_vehMarcaB;
    private String par_vehModeloB;
    private String par_vehRemolqueB;
    private String par_vehMatRemolB;
    private String par_segNombreB;
    private String par_segEmailB;
    private String par_segTelefonoB;
    private String par_conNombreB;
    private String par_conEmailB;
    private String par_conTelefonoB;
    private String par_danosB;
    private String par_circunstanciasB;

    public Parte(Context context)
    {
        this.context = context;
    }

    public Parte(long id, String par_fecha, String par_direccion, String par_ciudad, String par_codigoPostal,
                 String par_pais, String par_atestado, String par_herido, String par_distintosAB, String par_objetosA,
                 String par_testigos, String par_nombreTestigo, String par_telefonoTestigo, String par_latitud,
                 String par_longitud, String par_foto1, String par_foto2, String par_foto3, String par_aseNombreA,
                 String par_aseEmailA, String par_aseTelefonoA, String par_vehMatriculaA, String par_vehMarcaA,
                 String par_vehModeloA, String par_vehRemolqueA, String par_vehMatRemolA, String par_segNombreA,
                 String par_segEmailA, String par_segTelefonoA, String par_conNombreA, String par_conEmailA,
                 String par_conTelefonoA, String par_danosA, String par_circunstanciasA, String par_aseNombreB,
                 String par_aseEmailB, String par_aseTelefonoB, String par_vehMatriculaB, String par_vehMarcaB,
                 String par_vehModeloB, String par_vehRemolqueB, String par_vehMatRemolB, String par_segNombreB,
                 String par_segEmailB, String par_segTelefonoB, String par_conNombreB, String par_conEmailB,
                 String par_conTelefonoB, String par_danosB, String par_circunstanciasB)
    {
        this.id = id;
        this.par_fecha = par_fecha;
        this.par_direccion = par_direccion;
        this.par_ciudad = par_ciudad;
        this.par_codigoPostal = par_codigoPostal;
        this.par_pais = par_pais;
        this.par_atestado = par_atestado;
        this.par_herido = par_herido;
        this.par_distintosAB = par_distintosAB;
        this.par_objetosA = par_objetosA;
        this.par_testigos = par_testigos;
        this.par_nombreTestigo = par_nombreTestigo;
        this.par_telefonoTestigo = par_telefonoTestigo;
        this.par_latitud = par_latitud;
        this.par_longitud = par_longitud;
        this.par_foto1 = par_foto1;
        this.par_foto2 = par_foto2;
        this.par_foto3 = par_foto3;
        this.par_aseNombreA = par_aseNombreA;
        this.par_aseEmailA = par_aseEmailA;
        this.par_aseTelefonoA = par_aseTelefonoA;
        this.par_vehMatriculaA = par_vehMatriculaA;
        this.par_vehMarcaA = par_vehMarcaA;
        this.par_vehModeloA = par_vehModeloA;
        this.par_vehRemolqueA = par_vehRemolqueA;
        this.par_vehMatRemolA = par_vehMatRemolA;
        this.par_segNombreA = par_segNombreA;
        this.par_segEmailA = par_segEmailA;
        this.par_segTelefonoA = par_segTelefonoA;
        this.par_conNombreA = par_conNombreA;
        this.par_conEmailA = par_conEmailA;
        this.par_conTelefonoA = par_conTelefonoA;
        this.par_danosA = par_danosA;
        this.par_circunstanciasA = par_circunstanciasA;
        this.par_aseNombreB = par_aseNombreB;
        this.par_aseEmailB = par_aseEmailB;
        this.par_aseTelefonoB = par_aseTelefonoB;
        this.par_vehMatriculaB = par_vehMatriculaB;
        this.par_vehMarcaB = par_vehMarcaB;
        this.par_vehModeloB = par_vehModeloB;
        this.par_vehRemolqueB = par_vehRemolqueB;
        this.par_vehMatRemolB = par_vehMatRemolB;
        this.par_segNombreB = par_segNombreB;
        this.par_segEmailB = par_segEmailB;
        this.par_segTelefonoB = par_segTelefonoB;
        this.par_conNombreB = par_conNombreB;
        this.par_conEmailB = par_conEmailB;
        this.par_conTelefonoB = par_conTelefonoB;
        this.par_danosB = par_danosB;
        this.par_circunstanciasB = par_circunstanciasB;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPar_fecha() {
        return par_fecha;
    }

    public void setPar_fecha(String par_fecha) {
        this.par_fecha = par_fecha;
    }

    public String getPar_direccion() {
        return par_direccion;
    }

    public void setPar_direccion(String par_direccion) {
        this.par_direccion = par_direccion;
    }

    public String getPar_ciudad() {
        return par_ciudad;
    }

    public void setPar_ciudad(String par_ciudad) {
        this.par_ciudad = par_ciudad;
    }

    public String getPar_codigoPostal() {
        return par_codigoPostal;
    }

    public void setPar_codigoPostal(String par_codigoPostal) {
        this.par_codigoPostal = par_codigoPostal;
    }

    public String getPar_pais() {
        return par_pais;
    }

    public void setPar_pais(String par_pais) {
        this.par_pais = par_pais;
    }

    public String getPar_atestado() {
        return par_atestado;
    }

    public void setPar_atestado(String par_atestado) {
        this.par_atestado = par_atestado;
    }

    public String getPar_herido() {
        return par_herido;
    }

    public void setPar_herido(String par_herido) {
        this.par_herido = par_herido;
    }

    public String getPar_distintosAB() {
        return par_distintosAB;
    }

    public void setPar_distintosAB(String par_distintosAB) {
        this.par_distintosAB = par_distintosAB;
    }

    public String getPar_objetosA() {
        return par_objetosA;
    }

    public void setPar_objetosA(String par_objetosA) {
        this.par_objetosA = par_objetosA;
    }

    public String getPar_testigos() {
        return par_testigos;
    }

    public void setPar_testigos(String par_testigos) {
        this.par_testigos = par_testigos;
    }

    public String getPar_nombreTestigo() {
        return par_nombreTestigo;
    }

    public void setPar_nombreTestigo(String par_nombreTestigo) {
        this.par_nombreTestigo = par_nombreTestigo;
    }

    public String getPar_telefonoTestigo() {
        return par_telefonoTestigo;
    }

    public void setPar_telefonoTestigo(String par_telefonoTestigo) {
        this.par_telefonoTestigo = par_telefonoTestigo;
    }

    public String getPar_latitud() {
        return par_latitud;
    }

    public void setPar_latitud(String par_latitud) {
        this.par_latitud = par_latitud;
    }

    public String getPar_longitud() {
        return par_longitud;
    }

    public void setPar_longitud(String par_longitud) {
        this.par_longitud = par_longitud;
    }

    public String getPar_foto1() {
        return par_foto1;
    }

    public void setPar_foto1(String par_foto1) {
        this.par_foto1 = par_foto1;
    }

    public String getPar_foto2() {
        return par_foto2;
    }

    public void setPar_foto2(String par_foto2) {
        this.par_foto2 = par_foto2;
    }

    public String getPar_foto3() {
        return par_foto3;
    }

    public void setPar_foto3(String par_foto3) {
        this.par_foto3 = par_foto3;
    }

    public String getPar_aseNombreA() {
        return par_aseNombreA;
    }

    public void setPar_aseNombreA(String par_aseNombreA) {
        this.par_aseNombreA = par_aseNombreA;
    }

    public String getPar_aseEmailA() {
        return par_aseEmailA;
    }

    public void setPar_aseEmailA(String par_aseEmailA) {
        this.par_aseEmailA = par_aseEmailA;
    }

    public String getPar_aseTelefonoA() {
        return par_aseTelefonoA;
    }

    public void setPar_aseTelefonoA(String par_aseTelefonoA) {
        this.par_aseTelefonoA = par_aseTelefonoA;
    }

    public String getPar_vehMatriculaA() {
        return par_vehMatriculaA;
    }

    public void setPar_vehMatriculaA(String par_vehMatriculaA) {
        this.par_vehMatriculaA = par_vehMatriculaA;
    }

    public String getPar_vehMarcaA() {
        return par_vehMarcaA;
    }

    public void setPar_vehMarcaA(String par_vehMarcaA) {
        this.par_vehMarcaA = par_vehMarcaA;
    }

    public String getPar_vehModeloA() {
        return par_vehModeloA;
    }

    public void setPar_vehModeloA(String par_vehModeloA) {
        this.par_vehModeloA = par_vehModeloA;
    }

    public String getPar_vehRemolqueA() {
        return par_vehRemolqueA;
    }

    public void setPar_vehRemolqueA(String par_vehRemolqueA) {
        this.par_vehRemolqueA = par_vehRemolqueA;
    }

    public String getPar_vehMatRemolA() {
        return par_vehMatRemolA;
    }

    public void setPar_vehMatRemolA(String par_vehMatRemolA) {
        this.par_vehMatRemolA = par_vehMatRemolA;
    }

    public String getPar_segNombreA() {
        return par_segNombreA;
    }

    public void setPar_segNombreA(String par_segNombreA) {
        this.par_segNombreA = par_segNombreA;
    }

    public String getPar_segEmailA() {
        return par_segEmailA;
    }

    public void setPar_segEmailA(String par_segEmailA) {
        this.par_segEmailA = par_segEmailA;
    }

    public String getPar_segTelefonoA() {
        return par_segTelefonoA;
    }

    public void setPar_segTelefonoA(String par_segTelefonoA) {
        this.par_segTelefonoA = par_segTelefonoA;
    }

    public String getPar_conNombreA() {
        return par_conNombreA;
    }

    public void setPar_conNombreA(String par_conNombreA) {
        this.par_conNombreA = par_conNombreA;
    }

    public String getPar_conEmailA() {
        return par_conEmailA;
    }

    public void setPar_conEmailA(String par_conEmailA) {
        this.par_conEmailA = par_conEmailA;
    }

    public String getPar_conTelefonoA() {
        return par_conTelefonoA;
    }

    public void setPar_conTelefonoA(String par_conTelefonoA) {
        this.par_conTelefonoA = par_conTelefonoA;
    }

    public String getPar_danosA() {
        return par_danosA;
    }

    public void setPar_danosA(String par_danosA) {
        this.par_danosA = par_danosA;
    }

    public String getPar_circunstanciasA() {
        return par_circunstanciasA;
    }

    public void setPar_circunstanciasA(String par_circunstanciasA) {
        this.par_circunstanciasA = par_circunstanciasA;
    }

    public String getPar_aseNombreB() {
        return par_aseNombreB;
    }

    public void setPar_aseNombreB(String par_aseNombreB) {
        this.par_aseNombreB = par_aseNombreB;
    }

    public String getPar_aseEmailB() {
        return par_aseEmailB;
    }

    public void setPar_aseEmailB(String par_aseEmailB) {
        this.par_aseEmailB = par_aseEmailB;
    }

    public String getPar_aseTelefonoB() {
        return par_aseTelefonoB;
    }

    public void setPar_aseTelefonoB(String par_aseTelefonoB) {
        this.par_aseTelefonoB = par_aseTelefonoB;
    }

    public String getPar_vehMatriculaB() {
        return par_vehMatriculaB;
    }

    public void setPar_vehMatriculaB(String par_vehMatriculaB) {
        this.par_vehMatriculaB = par_vehMatriculaB;
    }

    public String getPar_vehMarcaB() {
        return par_vehMarcaB;
    }

    public void setPar_vehMarcaB(String par_vehMarcaB) {
        this.par_vehMarcaB = par_vehMarcaB;
    }

    public String getPar_vehModeloB() {
        return par_vehModeloB;
    }

    public void setPar_vehModeloB(String par_vehModeloB) {
        this.par_vehModeloB = par_vehModeloB;
    }

    public String getPar_vehRemolqueB() {
        return par_vehRemolqueB;
    }

    public void setPar_vehRemolqueB(String par_vehRemolqueB) {
        this.par_vehRemolqueB = par_vehRemolqueB;
    }

    public String getPar_vehMatRemolB() {
        return par_vehMatRemolB;
    }

    public void setPar_vehMatRemolB(String par_vehMatRemolB) {
        this.par_vehMatRemolB = par_vehMatRemolB;
    }

    public String getPar_segNombreB() {
        return par_segNombreB;
    }

    public void setPar_segNombreB(String par_segNombreB) {
        this.par_segNombreB = par_segNombreB;
    }

    public String getPar_segEmailB() {
        return par_segEmailB;
    }

    public void setPar_segEmailB(String par_segEmailB) {
        this.par_segEmailB = par_segEmailB;
    }

    public String getPar_segTelefonoB() {
        return par_segTelefonoB;
    }

    public void setPar_segTelefonoB(String par_segTelefonoB) {
        this.par_segTelefonoB = par_segTelefonoB;
    }

    public String getPar_conNombreB() {
        return par_conNombreB;
    }

    public void setPar_conNombreB(String par_conNombreB) {
        this.par_conNombreB = par_conNombreB;
    }

    public String getPar_conEmailB() {
        return par_conEmailB;
    }

    public void setPar_conEmailB(String par_conEmailB) {
        this.par_conEmailB = par_conEmailB;
    }

    public String getPar_conTelefonoB() {
        return par_conTelefonoB;
    }

    public void setPar_conTelefonoB(String par_conTelefonoB) {
        this.par_conTelefonoB = par_conTelefonoB;
    }

    public String getPar_danosB() {
        return par_danosB;
    }

    public void setPar_danosB(String par_danosB) {
        this.par_danosB = par_danosB;
    }

    public String getPar_circunstanciasB() {
        return par_circunstanciasB;
    }

    public void setPar_circunstanciasB(String par_circunstanciasB) {
        this.par_circunstanciasB = par_circunstanciasB;
    }

    public long getIdAnterior() {
        return idAnterior;
    }

    public void setIdAnterior(long idAnterior) {
        this.idAnterior = idAnterior;
    }

    public static Parte find(Context context, long id)
    {
        ParteDbAdapter dbAdapter = new ParteDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        Parte usuario = Parte.cursorToParte(context, c);

        c.close();

        return usuario;
    }

    public static Parte cursorToParte(Context context, Cursor c)
    {
        Parte parte = null;

        if (c != null)
        {
            parte = new Parte(context);

            parte.setId(c.getLong(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ID)));
            parte.setPar_fecha(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_FECHA)));
            parte.setPar_direccion(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_DIRECCION)));
            parte.setPar_ciudad(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CIUDAD)));
            parte.setPar_codigoPostal(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CODIGOPOSTAL)));
            parte.setPar_pais(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_PAIS)));
            parte.setPar_atestado(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ATESTADO)));
            parte.setPar_herido(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_HERIDO)));
            parte.setPar_distintosAB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_DISTINTOSAB)));
            parte.setPar_objetosA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_OBJETOSA)));
            parte.setPar_testigos(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_TESTIGOS)));
            parte.setPar_nombreTestigo(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_NOMBRE_TESTIGO)));
            parte.setPar_telefonoTestigo(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_TELEFONO_TESTIGO)));
            parte.setPar_latitud(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_LATITUD)));
            parte.setPar_longitud(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_LONGITUD)));

            parte.setPar_foto1(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_FOTO1)));
            parte.setPar_foto2(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_FOTO2)));
            parte.setPar_foto3(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_FOTO3)));

            parte.setPar_aseNombreA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ASENOMBRE_A)));
            parte.setPar_aseEmailA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ASEEMAIL_A)));
            parte.setPar_aseTelefonoA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ASETELEFONO_A)));
            parte.setPar_vehMatriculaA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMATRICULA_A)));
            parte.setPar_vehMarcaA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMARCA_A)));
            parte.setPar_vehModeloA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMODELO_A)));
            parte.setPar_vehRemolqueA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHREMOLQUE_A)));
            parte.setPar_vehMatRemolA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMATREMOL_A)));
            parte.setPar_segNombreA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_SEGNOMBRE_A)));
            parte.setPar_segEmailA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_SEGEMAIL_A )));
            parte.setPar_segTelefonoA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_SEGTELEFONO_A )));
            parte.setPar_conNombreA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CONNOMBRE_A)));
            parte.setPar_conEmailA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CONEMAIL_A )));
            parte.setPar_conTelefonoA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_TELEFONO_A)));
            parte.setPar_danosA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_DANOS_A)));
            parte.setPar_circunstanciasA(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CIRCUNSTANCIAS_A )));

            parte.setPar_aseNombreB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ASENOMBRE_B)));
            parte.setPar_aseEmailB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ASEEMAIL_B)));
            parte.setPar_aseTelefonoB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_ASETELEFONO_B)));
            parte.setPar_vehMatriculaB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMATRICULA_B)));
            parte.setPar_vehMarcaB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMARCA_B)));
            parte.setPar_vehModeloB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMODELO_B)));
            parte.setPar_vehRemolqueB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHREMOLQUE_B)));
            parte.setPar_vehMatRemolB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_VEHMATREMOL_B)));
            parte.setPar_segNombreB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_SEGNOMBRE_B)));
            parte.setPar_segEmailB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_SEGEMAIL_B )));
            parte.setPar_segTelefonoB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_SEGTELEFONO_B)));
            parte.setPar_conNombreB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CONNOMBRE_B)));
            parte.setPar_conEmailB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CONEMAIL_B)));
            parte.setPar_conTelefonoB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_TELEFONO_B)));
            parte.setPar_danosB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_DANOS_B)));
            parte.setPar_circunstanciasB(c.getString(c.getColumnIndex(ParteDbAdapter.C_COLUMNA_CIRCUNSTANCIAS_B)));
        }
        return parte;
    }

    private ContentValues toContentValues()
    {
        ContentValues reg = new ContentValues();

        reg.put(ParteDbAdapter.C_COLUMNA_ID, this.getId());
        reg.put(ParteDbAdapter.C_COLUMNA_FECHA, this.getPar_fecha());
        reg.put(ParteDbAdapter.C_COLUMNA_DIRECCION, this.getPar_direccion());
        reg.put(ParteDbAdapter.C_COLUMNA_CIUDAD, this.getPar_ciudad());
        reg.put(ParteDbAdapter.C_COLUMNA_CODIGOPOSTAL, this.getPar_codigoPostal());
        reg.put(ParteDbAdapter.C_COLUMNA_PAIS, this.getPar_pais());
        reg.put(ParteDbAdapter.C_COLUMNA_ATESTADO, this.getPar_atestado());
        reg.put(ParteDbAdapter.C_COLUMNA_HERIDO, this.getPar_herido());
        reg.put(ParteDbAdapter.C_COLUMNA_DISTINTOSAB, this.getPar_distintosAB());
        reg.put(ParteDbAdapter.C_COLUMNA_OBJETOSA, this.getPar_objetosA());
        reg.put(ParteDbAdapter.C_COLUMNA_TESTIGOS, this.getPar_testigos());
        reg.put(ParteDbAdapter.C_COLUMNA_NOMBRE_TESTIGO, this.getPar_nombreTestigo());
        reg.put(ParteDbAdapter.C_COLUMNA_TELEFONO_TESTIGO, this.getPar_telefonoTestigo());
        reg.put(ParteDbAdapter.C_COLUMNA_LATITUD, this.getPar_latitud());
        reg.put(ParteDbAdapter.C_COLUMNA_LONGITUD, this.getPar_longitud());

        reg.put(ParteDbAdapter.C_COLUMNA_FOTO1, this.getPar_foto1());
        reg.put(ParteDbAdapter.C_COLUMNA_FOTO2, this.getPar_foto2());
        reg.put(ParteDbAdapter.C_COLUMNA_FOTO3, this.getPar_foto3());

        reg.put(ParteDbAdapter.C_COLUMNA_ASENOMBRE_A, this.getPar_aseNombreA());
        reg.put(ParteDbAdapter.C_COLUMNA_ASEEMAIL_A, this.getPar_aseEmailA());
        reg.put(ParteDbAdapter.C_COLUMNA_ASETELEFONO_A, this.getPar_aseTelefonoA());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMATRICULA_A, this.getPar_vehMarcaA());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMARCA_A, this.getPar_vehMarcaA());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMODELO_A, this.getPar_vehModeloA());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHREMOLQUE_A, this.getPar_vehRemolqueA());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMATREMOL_A, this.getPar_vehMatRemolA());
        reg.put(ParteDbAdapter.C_COLUMNA_SEGNOMBRE_A, this.getPar_segNombreA());
        reg.put(ParteDbAdapter.C_COLUMNA_SEGEMAIL_A, this.getPar_segEmailA());
        reg.put(ParteDbAdapter.C_COLUMNA_SEGTELEFONO_A, this.getPar_segTelefonoA());
        reg.put(ParteDbAdapter.C_COLUMNA_CONNOMBRE_A, this.getPar_conNombreA());
        reg.put(ParteDbAdapter.C_COLUMNA_CONEMAIL_A, this.getPar_conEmailA());
        reg.put(ParteDbAdapter.C_COLUMNA_TELEFONO_A, this.getPar_conTelefonoA());
        reg.put(ParteDbAdapter.C_COLUMNA_DANOS_A, this.getPar_danosA());
        reg.put(ParteDbAdapter.C_COLUMNA_CIRCUNSTANCIAS_A, this.getPar_circunstanciasA());

        reg.put(ParteDbAdapter.C_COLUMNA_ASENOMBRE_B, this.getPar_aseNombreB());
        reg.put(ParteDbAdapter.C_COLUMNA_ASEEMAIL_B, this.getPar_aseEmailB());
        reg.put(ParteDbAdapter.C_COLUMNA_ASETELEFONO_B, this.getPar_aseTelefonoB());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMATRICULA_B, this.getPar_vehMarcaB());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMARCA_B, this.getPar_vehMarcaB());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMODELO_B, this.getPar_vehModeloB());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHREMOLQUE_B, this.getPar_vehRemolqueB());
        reg.put(ParteDbAdapter.C_COLUMNA_VEHMATREMOL_B, this.getPar_vehMatRemolB());
        reg.put(ParteDbAdapter.C_COLUMNA_SEGNOMBRE_B, this.getPar_segNombreB());
        reg.put(ParteDbAdapter.C_COLUMNA_SEGEMAIL_B, this.getPar_segEmailB());
        reg.put(ParteDbAdapter.C_COLUMNA_SEGTELEFONO_B, this.getPar_segTelefonoB());
        reg.put(ParteDbAdapter.C_COLUMNA_CONNOMBRE_B, this.getPar_conNombreB());
        reg.put(ParteDbAdapter.C_COLUMNA_CONEMAIL_B, this.getPar_conEmailB());
        reg.put(ParteDbAdapter.C_COLUMNA_TELEFONO_B, this.getPar_conTelefonoB());
        reg.put(ParteDbAdapter.C_COLUMNA_DANOS_B, this.getPar_danosB());
        reg.put(ParteDbAdapter.C_COLUMNA_CIRCUNSTANCIAS_B, this.getPar_circunstanciasB());
        return reg;
    }

    public long save() {
        ParteDbAdapter dbAdapter = new ParteDbAdapter(this.getContext());

        if (dbAdapter.exists(this.getId())) {
            long i = dbAdapter.registros();
            i++;
            this.setId(i);
            dbAdapter.insert(this.toContentValues());
        } else{
            if (id == 0) {
                dbAdapter.insert(this.toContentValues());
            }
        }
        this.setIdAnterior(this.getId());
        return this.getId();
    }
}
