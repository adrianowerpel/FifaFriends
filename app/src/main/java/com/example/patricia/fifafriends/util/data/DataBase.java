package com.example.patricia.fifafriends.util.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by patricia on 29/03/2018.
 */

public class DataBase extends SQLiteOpenHelper{

    public static final String dataBaseName = "fifaFirends.db";

    public static final int dataBaseVersion = 1;

    public DataBase(Context context) {
        super(context, dataBaseName, null, dataBaseVersion);
    }

    public static final String TbTimes = "Times";
    public static final String FdId = "id";
    public static final String FdNome = "nome";
    public static final String FdPontos = "pontos";
    public static final String FdAtivo = "ativo";

    String sqlTimes = "create table "+TbTimes+"(\n" +
            ""+FdId+" int,\n" +
            ""+FdNome+" varchar(30),\n" +
            ""+FdPontos+" double,\n" +
            ""+FdAtivo+" bool\n" +
            ");";

    public static final String TbJogadores = "jogadoresTime";
    public static final String FdIdJogador = "id_jogador";
    public static final String FdNomeJogador = "nome";
    public static final String FdPosicao = "posicao";
    public static final String FdGols = "gols";
    public static final String FkIdTime = "id_time";

    String sqlJogadoresTime = "create table "+TbJogadores+"(\n" +
            ""+FdIdJogador+" int,\n" +
            ""+FdNomeJogador+" varchar(50),\n" +
            ""+FdPosicao+" varchar(20),\n" +
            ""+FdGols+" int,\n" +
            ""+FkIdTime+" int,\n" +
            "constraint fdk_idTime foreign key("+FkIdTime+") references "+TbTimes+"("+FdId+")\n" +
            ");";

    public static final String Tbamigo = "amigo";
    public static final String FdIdAmigo = "id_amigo";
    public static final String FdNomeAmigo = "nome";

    String sqlAmigo = "create table "+Tbamigo+"(\n" +
            ""+FdIdAmigo+" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            ""+FdNomeAmigo+" varchar(30),\n" +
            ""+FkIdTime+" int,\n" +
            "constraint fdk_time_amigo foreign key("+FkIdTime+") references "+TbTimes+"("+FdId+")\n" +
            ");";

    public static final String TbTabela = "tabela";
    public static final String FdIdTabela = "id_tabela";

    String sqlTabela = "create table "+TbTabela+"(\n" +
            ""+FdIdTabela+" int,\n" +
            ""+FkIdTime+" int,\n" +
            "constraint fdk_time_tabela foreign key("+FkIdTime+") references "+TbTimes+"("+FdId+")\n" +
            ");";

    public static final String TbArtilheiro = "artilheiro";
    public static final String FdIdartilheiro = "id_tabela";
    public static final String FkIdJogador = "id_jogador";

    String sqlArtilheiro = "create table "+TbArtilheiro+"(\n" +
            ""+FdIdartilheiro+" int,\n" +
            ""+FkIdJogador+" int,\n" +
            "constraint fdk_jogador foreign key("+FkIdJogador+") references "+TbJogadores+"("+FdIdJogador+")\n" +
            ");";

    public static final String tbJogosRodada = "jogosRodada";
    public static final String FdIdJogosRodada = "id_jogo_rodada";
    public static final String FdGolsRodadaTime1 = "gols_rodada_time1";
    public static final String FdGolsRodadaTime2 = "gols_rodada_time2";
    public static final String FkIdTime1 = "fk_id_time1";
    public static final String FkIdTime2 = "fk_id_time2";

    String sqlJogosRodada = "create table "+tbJogosRodada+"(" +
            ""+FdIdJogosRodada+" integer primary key autoincrement," +
            ""+FdGolsRodadaTime1+" int," +
            ""+FdGolsRodadaTime2+" int," +
            ""+FkIdTime1+" int," +
            ""+FkIdTime2+" int)";

    String sqlInsertTimes = "insert into "+TbTimes+" ("+FdId+","+FdNome+","+FdPontos+","+FdAtivo+") values (1,'Atletico MG',0,0),\n" +
            "(2,'Botafogo',0,0),\n" +
            "(3,'São Paulo',0,0),\n" +
            "(4,'Internacional',0,0),\n" +
            "(5,'Cruzeiro',0,0),\n" +
            "(6,'Vasco',0,0),\n" +
            "(7,'Palmeiras',0,0),\n" +
            "(8,'Gremio',0,0);";

    String sqlInsertJogadores = "insert into "+TbJogadores+" ("+FdNomeJogador+","+FdPosicao+","+FdGols+","+FkIdTime+") values "+
            "('Victor','goleiro',0,1),\n" +
            "('Leonardo Silva','zagueiro',0,1),\n" +
            "('Otero','meia',0,1),\n" +
            "('Luan','atacante',0,1),\n" +
            "('Ricardo Oliveira','atacante',0,1),\n" +
            "('Gatito','goleiro',0,2),\n" +
            "('Igor Rabello','zagueiro',0,2),\n" +
            "('Renatinho','meia',0,2),\n" +
            "('Rodrigo Pimpão','atacante',0,2),\n" +
            "('Kieza','atacante',0,2),\n" +
            "('Sidão','goleiro',0,3),\n" +
            "('Rodrigo Caio','zagueiro',0,3),\n" +
            "('Nene','meia',0,3),\n" +
            "('Cueva','meia',0,3),\n" +
            "('Trellez','atacante',0,3),\n" +
            "('Marcelo Lomba','goleiro',0,4),\n" +
            "('Victor Cuesta','zagueiro',0,4),\n" +
            "('Camilo','meia',0,4),\n" +
            "('Lenadro Damião','atacante',0,4),\n" +
            "('Willian Pottker','atacante',0,4),\n" +
            "('Fabio','goleiro',0,5),\n" +
            "('Dedé','zagueiro',0,5),\n" +
            "('Thiago Neves','meia',0,5),\n" +
            "('Sassá','atacante',0,5),\n" +
            "('Rafael Sobis','atacante',0,5),\n" +
            "('Martin Silva','goleiro',0,6),\n" +
            "('Paulão','zagueiro',0,6),\n" +
            "('Wagner','meia',0,6),\n" +
            "('Andres Rios','atacante',0,6),\n" +
            "('Riascos','atacante',0,6),\n" +
            "('Jailson','goleiro',0,7),\n" +
            "('Antonion Carlos','zagueiro',0,7),\n" +
            "('Lucas Lima','meia',0,7),\n" +
            "('Borja','atacante',0,7),\n" +
            "('Keno','atacante',0,7),\n" +
            "('Marcelo Grhoe','goleiro',0,8),\n" +
            "('Geromel','zagueiro',0,8),\n" +
            "('Ramiro','meia',0,8),\n" +
            "('Michael','meia',0,8),\n" +
            "('Jael','atacante',0,8);";

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Gravando as tabelas no SQLite
        db.execSQL(sqlTimes);
        db.execSQL(sqlJogadoresTime);
        db.execSQL(sqlAmigo);
        db.execSQL(sqlTabela);
        db.execSQL(sqlArtilheiro);
        db.execSQL(sqlJogosRodada);

        //Inserindo dados nas tabelas Times e Jogadores
        db.execSQL(sqlInsertTimes);
        db.execSQL(sqlInsertJogadores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE "+TbTimes);
        db.execSQL("DROP TABLE "+TbJogadores);
        db.execSQL("DROP TABLE "+Tbamigo);
        db.execSQL("DROP TABLE "+TbTabela);
        db.execSQL("DROP TABLE "+TbArtilheiro);
        db.execSQL("DROP TABLE "+tbJogosRodada);

        onCreate(db);

    }
}
