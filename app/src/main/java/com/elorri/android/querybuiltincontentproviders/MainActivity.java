package com.elorri.android.querybuiltincontentproviders;


import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private DbHelper openHelper;
    private SQLiteDatabase db;


    private String mCalendarName;


    private String mContactName;
    private String mSatisfaction;
    private String mExpectedResponseTimeLimit;
    private String mIncreasedExpectedResponseTimeLimit;
    private String mFrequencyOfContact;
    private String mLastSatisfactionUpdate;
    private String mUnfollowed;
    private String mSatisfactionUnknown;
    private String mBackgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        openHelper = new DbHelper(mContext);
        db = openHelper.getWritableDatabase();

        removeTieUsContactFromContactData();

//        addTieUsActionsToProfileData();

//        addTieUsContactToContactData("100 digital", "2130837641", null, null, null, null, "1","0", "-8812853");
//        addTieUsContactToContactData("2adweb", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("2bhl", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("2c informatique", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("2j impression", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("3d manager coordination", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("3w", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("64 connections", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("64 telecom", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("A2gd sas", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("AEIA", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("ASB Informatique", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("ATOS WORLDGRID", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Aac", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Ab interim", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Abl technics", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Ac 020", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Acajou distribution", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Acces industrie", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Access data", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Access france", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Acei", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Aclea", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Acp64", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Actavision", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Acticall", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Action bureautique", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Action totale securite privee", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Activ design", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Actuel buro", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Adapei", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Adapei ce", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Adaya consulting", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Addeo", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Addinsoft", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Adecco", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Adequat", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Adequat systeme", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Adiome", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Adour etudes", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Adstellam", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Aed expertises", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Aequalis", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Aexium", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Ag recrutement", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Agce", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Agence Départ Numerique Des PA", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Agglo pau", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Agile prod", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Agpm gestion", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Ainolabs", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Airbnb Lucie", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Aisling Murphy", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Akka", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Akka ce", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Alear", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Algo tech informatique", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Alios pyrenees", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Allain kaltenbach plaisant ramon", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Alliadis", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Alliance energies", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Allplan france", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Alphatech ingenierie", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Alphéa Conseil", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Altanoveo", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Altansia", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Alteak", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Altedia", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Alten", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Altena informatique", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Altenia pro", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Altoneo conseil", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Altran technologies", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Am trust", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Amaia", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Ambroisie holding", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Amelie Deramond Bonnet", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Amelie Georges", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Amenagement innovation developpement", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Amoddex", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Amoes", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Analyse traitement ingenierie services", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Anco", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Angelique", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Angélique", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Anibio consultants", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Anne", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Anne Bonnaud Myscript", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Anne Gastellussary", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Anse gouraud distribution", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Antea france", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Anteis", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Anthelios", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Antoine", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Antoine Rouault de Coligny", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Apave sudeurope sas", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Apexa", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Aplon", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Apole Pau", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Appart Aragon", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Appli pour tous", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Applicatif bureautique communicat info", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Application euro technologie services", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Applications specialisees pour la gmao", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Applicatour", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Applied technologies internet", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Apres", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Apside", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Apx integration", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Aquinov", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Aquitaine de gestion urbaine & rurale", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Aquitaine ingenierie industrielle", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Aquitaine maitrise d oeuvre", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Aquitem", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Aract nouvelle aquitaine", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Aragon", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Aragon 2", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Aragon 3", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Argedis", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Armar", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Armatis ce", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Arobase conseil", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Arobase travail temporaire", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Arobasque", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Arobiz", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Arpajou organisation", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Arpiae", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Artelia", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Artfact", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Arts comm conseil", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Ary s barber", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("As4soft", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Asma", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Asobo studio", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Asp france", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Asp64", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Ass assistance totale a domicile", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Ass dep information logement pyr atl", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Ass dir interdiocesaine", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Ass espace vie pour adultes handicapes", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Ass gestion maison accueil altenberg", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Ass interprofessionnelle bio reg", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Assistances controles technologies", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Assisteaux ass tech tot ts trait eaux", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Asso kibouge", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Asso site web bearn gaves bearn des ga", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Association du cetir", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Association eurolacq entreprises", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Association in media", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Association jessica france", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Association solidarite travail", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Ateliers caugant", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Atexis france", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Atos", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Atos ce", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Atout logiciel", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Auberge Berreix", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Auberge Logibar", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Audensiel technologies", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Audit adour orthez", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Audrey", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Aurelia Milhe", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Aurelie Baros", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Aurelien", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Avista", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Axa", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Axe et cible", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Axe informatique", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Axilia", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Axiome", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Axs medical", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Axweb fr", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Azendoo", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("B districarb", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("B micro informatique", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Bab interim", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Barreault lafon ce", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Bassetti", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Bd energy", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Be bold", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Bearn informatique sarl", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Bearn prestations service", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Belharra", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Beneficom", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Bernadberoy ingenierie", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Bg partners", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Bge landes tec ge coop", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Bigot automobiles chateaudun", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Bilou services", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Biotope", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Bk systemes", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Bmso", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Bob el web", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Boulevard du nord", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Bouygues", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Bouygues ce", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Bpce", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Bpce ce", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Brainify", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Branchezrugby fr", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Brennus", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Britis services", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Brozerly", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Bst", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Bulle communication", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Bureau alpes controles", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Burkard dev", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("C2rt entreprise", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("C3s", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Cab d etudes tech electriques", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Cab etudes techniques rurales agricole", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Cab s industrie", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Cabinet arraou l epargne conseil", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Cabinet barrere", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Cabinet wagret", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Caisse centrale activite sociale", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Calliweb", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Canon france business services", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Cap terre", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Cap100t", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Capgemini sogeti", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Capgemini sogeti ce", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Carnor", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Carrieres & insertion", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Carte +", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Casagec ingenierie", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Cat amania", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Cdiscount", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Cegedim logiciels medicaux france", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Cegid public", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Ceiom", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Celad", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Celios conseils", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Centre de metrologie de l adour", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Centre gestion agree des pays de l ado", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Certimmo", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Cetab", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Cfatec", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Cga 33", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Cgc dgfip ce", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Cgi", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Cgi ce", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Challenge interim", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Chami", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Chapitre premier", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Chequeboutique com", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Chibko", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Chopard automobiles", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Christine", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Chronos interim", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Chu bordeaux", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Cimpa", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Cito informatique", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Cityhood", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Cjm international", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Cle", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Clichy auto service", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Clicknwax", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Clim conseil", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Climelec", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Clip industrie", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Cluster bateko", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Co di tra", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Cogniteev", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Comite local etablissement", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Comm", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Commune de altenach", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Commune de kaltenhouse", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Commune de nothalten", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Commune de westhalten", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Commune de wissembourg", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Communication avance ce", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Compact", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Compare primes fr", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Concept ingenierie systemes d info sa", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Conception devel publication multimedi", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Connectt", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Consept ingenierie", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Coorp", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Copenor ce", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Copy sud", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Corexpert", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Coriolis composites technologies", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Cosilog", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Cosoluce", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Cote basque etudes", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Couquillou", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Crausaz pere et fils sarl", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Creation du web", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Creative", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Credit agric technologies et sce", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Credit mutuel", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Creocean", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Crit", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Crit ce", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Critt", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Critt ce", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Crouzet automatismes ce", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Cs systemes d'information", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Cshpb", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Ctre etude infor oenol cadillacpodensa", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Ctre etude recherche tech indust mecan", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Ctre gestion agree conseil formation", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Ctre interinstit bilan competence", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Curistec", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Cv associes", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Cv associes engineering", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Cyber2rien", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Cybertech computer", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("D entree de jeux", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Daly soft", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Data web solutions", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Datagates", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Davan technicentre", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("David cousin", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Davidson", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Davidson consulting", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Davison", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Dax emploi formation insert", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Deal informatique", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Definima", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Dekra industrial", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Deltafluid", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Denis Bouzon", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Dental o", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Detection fuites multi sce aquitaine", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Dev1 o", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Devao", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Develop formation instal informatique", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Digital soft code", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Digiway", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Dimo software", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Distribution carburant riviere-pilote", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Distribution petroliere ozier lafontai", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Docone", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Documents image solutions", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Domnet services", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Dotlol", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Duo diag", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Développeur c", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Développeur cloud", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Développeur ios", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Développeur java", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Développeur mainframe", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Développeur mobile multiplateforms", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Développeur oracle forms", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Développeur web", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Développeur windows", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("E creatures", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("E makhila com", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("E media 64", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("E sy com", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("E topia fr", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("E transactions informatique", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("E trend", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Easy live", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Easycase", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Eboovisite", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Econocom", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Econocom ce", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Ecotech ingenierie", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Ecr environnement sud ouest", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Ecran total", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Ecta", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Eden auto", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Edi@net", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Edv sarl associates", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Egis", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Ekium", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("El habri", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Elcabe", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Electra ce", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Electricite auto mecanique schmidt", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Elina", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Elkar", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Ellyx", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Elres", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Email marketing", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Emo sarl", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Endo sport", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Energie plus", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Ennov clinical", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Enthalpia 2", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Entr de genie informatique & numerique", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Environnement sa", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Eras", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Erience", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Escore software", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Eshard", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Esi group", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Esprit bazar", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Essor", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Estct", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Etablissements lagarde", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Etablissements maynadier", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Etablissements moquette", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Etablissements richier", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Etourism experts", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Eureka", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Euris", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Eurisk", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Eurl station le bouvet", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Euro engineering", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Euro information services", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Euroedi", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Everwin", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Evollis", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Evolutec ingenierie", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Exakis", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Exalto", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Expectra", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Expectra ce", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Expertise controle 3 d", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Expertises du sud ouest exso", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Eyharts Sandrine", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Ezakus", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("F2x productions", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Faradis", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Fathima Cassimjee", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Fcv groupe", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Fd apps", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Fedelene", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Federation depart ass locales admr", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Feleng", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Fiducial", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Financiere mas", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Fisalez ressources humaines", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Flags group", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Fondasol", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Fondation Apprentis d’Auteuil", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Fondation jeunesse avenir entreprise", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Fonia", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Fonroche geothermie", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Forestry club de france", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Fortiori", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("France vehicules homologation", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Francis lavigne developpement", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Gabiro", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Gapjolk", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Gaudart", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Gdtech france", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Gedone technologies", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Genese informatique", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Gensun", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Geocoach", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Geolid", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Geolithe", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Geoloc systems", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Geomatika sarl", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Georex s a", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Geotec", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Germ services", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Gesadour", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Geser best", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Gest criee port st jean de luz ciboure", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Gestform", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Gestion administ martiniq entrepr sce", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Get 5", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Gfi", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Gfi ce", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Ginger cebtp", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Gleize energie service", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Gma conseil", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Gpt cooperation sanit medico social", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Gpt entr insert qualif hotel plein air", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Graphi ogre", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Graphique alliance", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Greenroom sas", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Groupe Medical Laennec", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Groupe etchart sa", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Groupement csms marie saint frai", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Groupement d employeurs viti prune", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Gruet ingenierie", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Gse regions", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Gso interactive", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Guarani", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Gustomember", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("H & b", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Ha discount", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Happiness", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Hardis groupe", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Harria holding", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Hauts de garonne developpement", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Hd2net", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Heimdall", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Heliantis", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Heliantis", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Helioprojet", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Hermet sarl", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Heurocom", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Hewlett packard france", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Hims2", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Hispano-suiza", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Hizkia", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Hodei", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Holding mesples", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Home technology management", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Hop doc", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Horoquartz", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Hotelina holding", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Hotelplan limited", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Hqml consult", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Hr path", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Hubvet", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Hupi", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Hydraulique environnement aquitaine", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Hydretudes grand sud pyrenees", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Ibac", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Icarice sas", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Iconcept", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Id systemes sa", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Idayvacances", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Idcom informatique", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Idsoft", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Ifr skeyes", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Igdis", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Image libre studio", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Imagine editions", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Immersive lab", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Implicit", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Incana", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Incatica", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Ined", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Infeeny", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Inflexsys", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Info paye conseil", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Infodirect sarl", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Informatique developpement micro", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Informatique etudes programmation", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Informatis technology systm", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Infotel", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Infotel ce", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Infrep", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Ingenerma", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Ingenierie concept et construction", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Ingenierie gilbert castaignede", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Ingenierie services", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Ingerop conseil et ingenierie ici", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Ingesol", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Ingeus", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Ingsoft", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Inra", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Inria", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Insee", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Inserm", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Insiteo", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Institut materiaux composites", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Intellysurf", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Interactive network technologies", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Interhome sarl", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Intersystems", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Investaq energie", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Irstea", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Is decisions", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Isl ingenierie", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Itchy", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Itema", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Iter", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Its inspection traitement services", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Ivelem", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Ixpand", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Izar developpements", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Jacob delafon ce", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Jambette service distribution", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Jar", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Jd develop", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Jean lutz sa", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Jls ramassamy", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Jobijoba", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Joz society", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Julie Coiffeuse", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Jvs mairistem", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Kaizen developments", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Kaltenbach constructions de machines", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Kerhis sas", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Kilo", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Kisamane", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Kitview", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Kontribu com", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("La chaine des artisans landais", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("La jaille 1", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Lab xxi", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Laborare conseil", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Laboratoire Analyses Casteljaloux", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Laboratoire ecci", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Laboratoire interprofessionnel aquitai", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Laboratoire labhya", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Laboratoires pyrenees landes", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Lacani", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Laetitia Spa Kemana", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Lafon", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Lafon ce", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Lalueza multi services", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Larive environnement", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Lci", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Le bonbon noir", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Le club", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Le hollandais volant", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Leader carbu services", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Lectra", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Lectra ce", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Leeftr", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Lekooa", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Les Origines", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Les artisans du net", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Les compagnons", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Les developpements durables", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Les residences du midi", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Les volants", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Libcast", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Lien interim insertion", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Light consultants", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Limeo interactive", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Linkweb", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Littoral production", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Logik64", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Lophitz", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("M vacs", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("M3c", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Madame agnes lafontaine", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Madame amelie castain", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Madame andra maria garrigue", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Madame audrey tarrago", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Madame caroline couet lannes", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Madame catherine hourgras", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Madame celine laguerre", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Madame charlotte sahut", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Madame christine le brazidec", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Madame christine theillac", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Madame claudia jaunet", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Madame elisabete correia de sousa", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Madame emilie lecuyer", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Madame emilie melan", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Madame evelyne lalanne dera", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Madame faustine francois", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Madame gabriela grindei", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Madame helene chauvin", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Madame isabelle nio", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Madame jenyfair lacour", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Madame judith reboul", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Madame julie larre brusset", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Madame julie lavinay", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Madame laetitia etcheverry", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Madame laetitia langeraert", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Madame laetitia ouley", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Madame laure beigbeder", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Madame laure garat", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Madame lea sampere", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Madame maria figueiredo", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Madame maria hernandez", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Madame marie lafargue", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Madame marine bragas", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Madame maryse guilbault", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Madame nathalie fons", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Madame pamela hamille", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Madame priscilla dufau", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Madame sabiatou njoya", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Madame sandra arwacher kemp", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Madame sandra lenoir", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Madame sandrine biard", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Madame sarah farcot", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Madame simala souvannavong", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Madame solene jolly", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Madame sylvie ceci", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Madame sylvine fau", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Madame virginie bompar", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Madame virginie loubere", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Madame virginie maudos", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Magsys", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Maif Ce", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Maillot distribution services", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Maisadour", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Maison le marquier", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Maison pierre oteiza", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Malta informatique", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Maltem insight performance", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Manpower", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Manpower ce", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Mapmedia", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Mapotempo", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Marie-Françoise Mergey", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Marina services distribution", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Maritime systems", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Martin", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Matis informations technologies", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Matsiya", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Maxsea international", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Md service", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Mdb tech", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Mecametric", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Med imaps", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Mediaboost", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Meosis", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Mercure formation", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Mgi consultants", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Michel arrayet", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Michel glinche", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Micro eisti", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Micromania", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Micronotes", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Micronotes", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Middle web", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Mirane", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Misteriel", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Mj developp - immob & investissement", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Mme Andree", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Mobiserv sas", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur adrien candau", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur adrien masanaba", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur alain pierre forsans", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur alan de la cruz", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur alexandre castets", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur alexandre martins de lima", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur alexandre nicoloso", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur alexandre orlowski", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur alexandre pere laperne", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur alexandre rodriguez", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur andre dimbour", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur antoine danielou", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur antoine ledoux", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur antonio brescia", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur arnaud loupy", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur arnaud sarrailh", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur arnaud sepa", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur arthur boivin", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur bastien moulia", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur bastien solhonne", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur benjamin delichere", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur benjamin denis", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur benoit etcheverry", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur bernard lagar rue", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur bertrand vernot", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur bruno bansard", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur bruno la malfa", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur cedric dufour", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur cedric moncade", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur cedric parisot", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur charles lafond", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur charly biscay", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur charly murcia", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur christophe leglise", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur christophe segura", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur clement casaban", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur clement duhart", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur cyril bosson", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur cyril duvic", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur cyril robert", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur cyrille gross", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur damien aguerre", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur damien boue", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur daniel daugas", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur david chlodny", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur david deplagne", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur david enrique", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur david page", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur david porre", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur david schwandt", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur dominique fiacek", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur dominique francois papin", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur dominique gerbaud", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur emmanuel cirou", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur emmanuel davant", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur emmanuel moro", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur eric batigne", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur eric dauteuille", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur eric hostalery", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur eric kuchta", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur eric lozes", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur eric sancerry", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur erwan lesire", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur fabien charles", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur fabien ferrere", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur fabien maindivide", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur florent latoures", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur florent polizzi", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur florian gabach", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur francis milhau", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur franck demongin", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur franck laforest", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur francois blate", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur francois charotte", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur francois patatut", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur frederic delgado", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur gabriel diez", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur gabriel gafari", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur gabriel lembege", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur gaetan camboulive", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur gaetan rouzies", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur gilles boutault", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur gilles puyfourcat", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur gregory larrieu", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur gregory liga", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur guillaume fritz", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur guillaume gruau", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur guillaume herisson", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur guillaume perrin", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur guillaume sasco", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur guy di meglio", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur hadrien mary", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur herve baltar", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur hugo martinez", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur hugues gosselin", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur idris naulleau", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur iker casares lopez", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur irwin lourtet", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur ivan guillot", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur ivrin minatchy", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur james lavignasse", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur javier mancisidor lusa", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur jean francois besnard", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur jean ithurralde", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur jean larzabal", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur jean louis laplace", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur jean michel bayle", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur jean noel haas", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur jean olivier gregoire", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur jean philippe brasier", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur jean pierre joignant", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur jean valery thoraval", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur jefferson jeremie carpy", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur jeremy capo", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur jeremy duez", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur jeremy peltier", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur jeremy prevost", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur jerome peguilhe", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur jerome teisseire", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur jesus munoz", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur jim titson", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur jimmy chagnaud", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur joel thevenin", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur jonathan wendlinger", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur jose juan eceiza iturzaeta", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur jose luis terrazas", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur jose miguel azpitarte salgado", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur jose sanchis", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur joseph fahey", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur julian maurin", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur juliano faraut", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur julien bidoret", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur julien conan", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur julien deschampt", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur julien dizdar", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur julien gafari", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur julien gbaguidi", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur julien kircher", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur julien le priol", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur julien levasseur", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur kevin chanardie", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur kevin faria fernandes", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur khalil cherdi", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur laurent castaings", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur laurent esclarmonde", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur laurent guarino", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur laurent guth", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur laurent hauet", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur laurent venutier", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur lionel vincent dicharry", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur lo c indart", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur loic choury", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur loic laborde", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur ludovic escoubet", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur ludovic lagouardette", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur ludovic le brun", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur ludovic royer", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur ludovic urios", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur marc duchesne", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur marc pena", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur markus kunze", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur martin merino martinez", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur marvin leroux", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur mathieu dauriac", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur mathieu dinguidart", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur mathieu legrand", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur mathieu loustau", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur mathieu pinatel", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur mathieu quesada", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur matthew ellis", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur matthieu bouron", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur maxime dublanc", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur maxime thierry lassalle", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur michael bergeret", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur michael cheloudtchenko", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur michael martin moro", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur michael roy", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur michel guibert", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur mickael fontaine", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur nabil reguia", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur nicolas fiascaro", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur nicolas guerin", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur nicolas lamouliatte", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur nicolas mercadieu", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur nicolas tallefourtane", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur nigel poulton", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur nils citoleux", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur noureddine kaous", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur olivier bignan", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur olivier cambon", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur olivier gerber", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur olivier loic leneveu", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur olivier pautrel", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur oscar ferreira", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur pascal elies", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur pascal valero sanz", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur patrice romevo", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur patrick dupuis", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur patrick lefevre", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur patxi berard", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur paul labonne", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur periko diaz", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur philippe baudry", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur philippe bellocq", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur philippe cazenave", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur philippe certa", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur philippe denis", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur philippe lauga", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Monsieur pierre baptiste bouillon", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur pierre cahingt", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur pierre laussu", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur pierre serres", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur quentin guillout", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur quentin tabart", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur rafael pereira", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur raymond cassel", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur remy cilia", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur remy remy", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur rene aouizerate", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Monsieur robin dyrda", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur romain cresson", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur samuel roy", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur sebastien domange", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Monsieur sebastien heissler-lapeyre", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur sebastien scherdlin", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur serge planes", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Monsieur simon faure", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur steeve cordier", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Monsieur stefane lipinski", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur stephane jones", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur sylvain nascimento", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur sylvain naveau", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur thibaud dufourcq brana", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur thibault genthon", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Monsieur thibault hicaubert", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur thierry dusoulie", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur thomas mirgon", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur thomas nares", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur thomas nicolay", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Monsieur thomas spierckel", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Monsieur timothee plante", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur tomas martin", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur valentin barit", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur victor pierre", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Monsieur vincent dupont", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Monsieur vincent larralde", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Monsieur vincent mimoun prat", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Monsieur vincent prat", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Monsieur xavier vachere", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Monsieur yannick bagneris", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Monsieur yoann guillot", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Monsieur yves lalanne", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Monsieur zanaye d almeida", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Moonda", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Morgan interim", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Msp media services partners", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Mureaux automobiles", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("My lodge", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("My money time", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Myscript", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Mywebcompany", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Mywebshop", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Nabil tnacheri conseil nt conseil", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Nalta systems", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Name of art", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Naro", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Nastica", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Neomades", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Neoptera", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Neosoft", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Neotech solutions", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Net sense", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Netick", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Netrinos", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Netshop design", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("New deal technology", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Nexource", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Nextgen rh", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Njuko", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Novae internet communication", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Novaldi sarl", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Novaresa", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Novasanco", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Np6", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Ns concept", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Nxo france", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("O tempora", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Oc developpement", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Octea ingenierie", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Octime", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Octime", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Ogeu groupe", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Ola si", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Op systemes", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Opale total negoce", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Open", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Open ce", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Operantis", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Operis", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Optima finances", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Optisol", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Orange", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Orange ce", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Ordiworld com", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Orthalis", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Oteis", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Otidea", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Ovsi", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Oxya france", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("P calestreme cs", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Pa", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Page personnel", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Palladium", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Panda one", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Panda services", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Parifex promo assist realisat indust", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Pau pyrenees actions", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Pausitic", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Pbs pau batiment services", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Pellidem", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Penaranda informatique", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Pepper internet", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Peridis", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Petris technology sas", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Pgi philippe garrote", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Phare", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Pharmagest interactive", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Phileas sommelier", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Pic digital", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Picture interactive", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Pierre Laporte", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Piment interim", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Pipe line service controle", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Pipeline engineering & services", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Playground", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Plus immobilier", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Pm2", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Pole e commerce", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Pole emploi", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Pole emploi ce", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Poly industries", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Populus alba", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Prev conseil", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Previmeteo", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Pricewaterhousecoopers advisory", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Primus soft", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Prince garage des petits ponts", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Proconcept", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Prodware", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Prodware ce", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Progema", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Programm interfaces pour l automatique", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Proman", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Prowebce", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Psy Hélène Méallet-Tantot", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Ptotal nonstop action wrestling", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Pyreweb", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Périchet mickael", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Qg websolutions", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Qualiconsult", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Quatorze heures", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Qucit", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Randstad", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Rb & associes", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Redmoot", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Relais ai", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Remote concept", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Reseau c&s", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Reseau chantier", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Reseau ehpad secteur bearn soule", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Restaurant belle vue altenstadt", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Restauval", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Retail global solutions", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Rimaonea", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Rno bymycar st dizier", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Robosoft services robots", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Romy carbu", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Route bleue automobile", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Rue des ecoles", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Sa garage carriere jean pierre", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Sacn societe automobile chauny noy", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Safege", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Safran", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Safran ce", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sage", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Saline cerebos", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sames wireless", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Sarl abi", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sarl astria 64", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Sarl ati", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Sarl becice", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sarl bottero", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sarl bourdin", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Sarl brunini et compagnie", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sarl contacts consulting", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Sarl courcelles autotech", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Sarl cyberjet", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Sarl energeco", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sarl etablissements majeste", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Sarl gmt conseil", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Sarl goudard", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sarl h2cr", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Sarl harada", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Sarl lang philippe", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sarl macon", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Sarl marian", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sarl paie & rh solutions", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Sarl sarrailh", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sarl station les cafes", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Sarl transports rey", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Sas odyssee gestion", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Saubesty lafforgue", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Scalian", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Scc sa", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Sce", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Sce etudes fabrication industrielles", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Sce tertiaires aux entr pyren step c", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Scop 47 elevage", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Scub", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Sd worx", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Sdm services", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Secomat", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Segula engineering france", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Sehh sarl", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Seldon fin sas", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Selecttt", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Selleco", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Sendao", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Septeo pole immobilier", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Sereb midi pyrenees", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Serv etudes conseils automatisme", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Servicap", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Services organisation methodes", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Services petroliers schlumberger", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Setavoo", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Setmo", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Setrel", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Sgs agri min", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Sgs oil gas & chemicals", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Shining", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Shoot shoot digital sarl", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Sig image", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sigma consulting", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Sii aix lyon ce", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Sii sud ouest ce", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Silicon graphics sa", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Simethis eurl", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Simtic", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Singularity insight", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Sitincloud", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Sitti", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Skill4web", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Skinlabs", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Slippers and bag", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Snapp", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Snecma", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Soc antill gestion station service", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Soc coop marit marins pecheurs guadelo", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Soc coordination ingenierie batiment", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Soc d etude plans assi coordin sepac", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Soc d expertise du sud ouest", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Soc expl des ets j m mailharro", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Soc exploitation ets pietri fils", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Soc exploitation relais de balagne", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Soc jean claude rouget immob financ", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Soc martiniquaise distri carburant sce", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Soc totalia", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Societe alpine de geotechnique", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Societe amazonienne de proprete", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Societe d exploitation totale", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Societe de diagraphies et de perforati", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Societe du garage meynier", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Societe europenne de garantie eurogara", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Societe morbihannaise diffusion automo", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Societe pour l informatique industriel", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Socotec", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Socrate conseils & formations", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Sodexo", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Sodi", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Sofarbi", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Sofresid engineering", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Softivert", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Softway medical radiologie", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Sogeca rh", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sollac florange ce", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Solutions developpement media", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sonovision", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Sopra Steria", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Sopra steria", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Sopra steria ce", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Spb family", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Sphere informatique", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Spie ics", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Spiral", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sprint logiciels", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Square achat", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("Start people", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Start people ce", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Starter", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Station moulin joli 2", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Station pente nicole", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Step up", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Studio virtu", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Sud carbu", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Sud ouest entretiens", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Sumtotal systems france", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Supersum check", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Supplay", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Surf me", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Swap and", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Sylde net", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Sylviane Brivial", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Synd cgt inscrits maritimes", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Synd gal livre communication ecrite cg", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Syndicat des inscrits maritimes", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Syndicat national de l ecrit cfdt", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Synercam", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Synergie", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Synergie informatique", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Syngenta", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Syrilio", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Tabulation", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Tag digital", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Tamaplace", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Tawenda", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Tcp", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Tdda totalement differents des autres", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Teamresa", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Tech advantage", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Tech valley", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Teeo", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Teklynx", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Telecom optique services", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Teledetection traitement images produc", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Tessi", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Texa services", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Thales", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Thales ce", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Theodore heid", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Therm eco engeneering", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Thesee", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Tibco services", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Tic partner", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Tigf", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Til technologies", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Tilhet materiaux services", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Timaco", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Tirel systems", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Tnt express france", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Tomo adour", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Topbiz software", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Total", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Total ce", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Totales creations massalia concept", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Totalinux", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Triangle 14", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Tu sais quoi ?", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Tuvedlacom", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Ugecam", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Unika concept", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Unit 4 business software france", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Urbanis", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Urrutian developpement", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Usiprocess", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Vacanceole", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Vahns", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Valbou", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Valorial", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Vardian", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Varioware", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Varius media group", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Vasco data security", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Vde sarl", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Vecteur plus", "2130837641", null, null, null, null, "1", "0", "-11549705");
//        addTieUsContactToContactData("Vectuel", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Vega informatique", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Vent d'ouest", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Ventana", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Vetocom", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Viris", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Virtuel developpement", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Vivalto", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Viveo france", "2130837641", null, null, null, null, "1", "0", "-5319295");
//        addTieUsContactToContactData("Vos memoires numeriques", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Vracoop", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Vtn ingenierie", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Waaz", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Wanao", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Waskr", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Webtao", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Weenove", "2130837641", null, null, null, null, "1", "0", "-8812853");
//        addTieUsContactToContactData("Weetab", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Well staff developpement", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Welljob interim", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("Wicqr", "2130837641", null, null, null, null, "1", "0", "-10929");
//        addTieUsContactToContactData("Window hero", "2130837641", null, null, null, null, "1", "0", "-18611");
//        addTieUsContactToContactData("Winparf", "2130837641", null, null, null, null, "1", "0", "-11677471");
//        addTieUsContactToContactData("Winups", "2130837641", null, null, null, null, "1", "0", "-30107");
//        addTieUsContactToContactData("Wopata", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Work 2000 distribution", "2130837641", null, null, null, null, "1", "0", "-4560696");
//        addTieUsContactToContactData("X trementerprise", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("Xahutu studio", "2130837641", null, null, null, null, "1", "0", "-1023342");
//        addTieUsContactToContactData("Xerox", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("Yellowstone software", "2130837641", null, null, null, null, "1", "0", "-8271996");
//        addTieUsContactToContactData("Yescapa", "2130837641", null, null, null, null, "1", "0", "-1739917");
//        addTieUsContactToContactData("Zazpi communication", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("Zenika", "2130837641", null, null, null, null, "1", "0", "-6982195");
//        addTieUsContactToContactData("Zimba", "2130837641", null, null, null, null, "1", "0", "-6190977");
//        addTieUsContactToContactData("Zippyware", "2130837641", null, null, null, null, "1", "0", "-2825897");
//        addTieUsContactToContactData("arobase conseil", "2130837641", null, null, null, null, "1", "0", "-10177034");
//        addTieUsContactToContactData("cechetto julien", "2130837641", null, null, null, null, "1", "0", "-7297874");
//        addTieUsContactToContactData("etchemendy oihana", "2130837641", null, null, null, null, "1", "0", "-11684180");
//        addTieUsContactToContactData("30Bananasaday .", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("ABALONE TT Abiliti", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Adecco", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Adeline Lopes", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Adéquat", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Anais LE SAUX", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Annaëlle Dufrenne", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Arobase Interim", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Atmos Intérim", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Axe Travail Temporaire", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("BIP INFO .", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Banque Populaire Dax", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Banque Populaire Dax 3", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Banque populaire Dax 2", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Barcelona Free Code Camp Meetup", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Baumert", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Beech A Tune", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Ben", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Benjamin Cadou", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Bernard Leparoux", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Bernard Mignon", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Bhcar", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Blabla Milene", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Blandine", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Blandine Conturie", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Boul'Pat Atlantique", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Bps Intérim", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Brançon Thomas", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Brief Me", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Bruno Vasta", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("C", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("CENTRAL TAXIS", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("CNAM", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Cabinet Gerard Bourdeu", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Cadouin", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Camille", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Camille Fraboulet", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Camille Thomas Graninger", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Cap Sud-Ouest", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Capgemini Sud - Sogeti", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Carpe Diem", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Celine Garlencq - STEP", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Challenge Intérim", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Childfree de france", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Cisco France", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Claire Anglet", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Clare", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Clare Blanchard", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Corep", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Couchsurfing", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Covoit Pierre Alex", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Crit", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Crêperie Tante Germaine", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Céline Laguerre", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("DUT MMI TARBES", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Damien Ponassie", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Daniel Germany", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Daniel Steinberg", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("David Brancaleone", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("David Verney", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("DaxAuto", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Decha Gilles", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Denis Naveau", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Diego Etchemendy", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Dom Service", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("E-city", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("E.P.I.I Entreprise Pyrénées Intérim Insertion .", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("ECOLE DES MINES D'ALES à PAU", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("ECOLE SUPERIEURE D'ART DES ROCAILLES DE LA COMMUNAUTE D'AGGLOMERATION DE BAYONNE ANGLET BIARRITZ à BIARRITZ", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("EISTI - École Internationale des Sciences du Traitement de l'Information .", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("ENSEIGNEMENT SUPERIEUR SAINT DOMINIQUE à PAU", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("EPSECO Bayonne", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("ESTIA à BIDART", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Ecole Nationale Supérieure En Génie Des Technologies Industrielles", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Ecole Supérieure De Commerce - ESC Pau", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Edwin", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Eric Lacau", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Estelle Et Pascal", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Etchemendy Daniel", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Etxepare Yves", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Eurolacq Entreprises", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Exia. CESI à PAU", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Eztitxu", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Eztitxu Etchemendy", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("FONDATION de FRANCE", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("FORMATION SUPERIEURE ADULTES - IEC à PAU", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("FOXYZ", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Fabienne", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Facebook .", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Fanette", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Fatima", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Fermier-fermiè", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Feuille De Com", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Fleur", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Fleuron Bio", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Florence OVS Bordeaux", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Florent Dicharry", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Florian", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Florian CS", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Fondation Agir Contre l’exclusion, (FACE)", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Fondation Agriculture Durable En Aquitaine", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Fondation De l’Armée Du Salut", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Fondation De l’Isle", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Fondation ESTIA, (Ecole supérieure Des Technologies avancées)", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Fondation Entreprise Et solidarité", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Fondation IBM France", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("FrAndroid .", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Francoise OVS Pau", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Francoise Vargues", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Fred", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Frederiq Fix", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Frederique", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Freedom", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("GDG Paris Android User Group .", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Gabi", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Gabriel Jourdane", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Gedone Technologies", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Gfi Informatique .", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Google+ .", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Gregory Darul", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Groupement Local D'employeurs Apbe", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Gustave Ngabo", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Gérard CAZALIS", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Helene", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Helio Graphic - Docuworld Group", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Houerie Laure", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("INSTITUT SAINT SIMON à TARBES", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("INSTITUT UNIVERSITAIRE DE FORMATION DES MAITRES D'AQUITAINE (IUFM) Ou ESCE à PAU", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("INSTITUT UNIVERSITAIRE DE TECHNOLOGIE PAUL SABATIER à TARBES", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("ITS Institut Du Travail Social Pau", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("IUT BAYONNE PAYS BASQUE", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Icc Informatique hiuss Des Bois", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Immersivelab", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Instagram .", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Intervia", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Jack Healy", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Jacky", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Jacky Peireira", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Jean", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Jean Baptiste et Maité Dabbadie", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Jean CHRIS ROS", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Jean Christophe Estoup", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Jean Claude et Françoise Douhaud", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Jean Yves", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Jose", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Julie RENAUD", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Julien Gruet", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Jy Clem", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Kam De Rochambeau", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Karine", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Katixa Sudre", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("LAB XXI", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("La Guinguette", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Laetitia", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Laetitia Sejourne", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Laida", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Landlord", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Laure Plumauzille", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Laurene Guillar", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Le Grenier A Pain", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Lilian Rosiere", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("LinkedIn .", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Lionel Lolaiz", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Lisa Parkin", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Lisa Parkins", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Lissar Eve", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Loic Douhaud", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Loué Leclerc", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Lucille", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Lucille Queruel", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Ludovic BlaBlaCar", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Luxi Oliarburu", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("MGI CONSULTANTS", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Maaf Anglet", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Magalie Lafaille", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Maire Billère", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Mairie Dax", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Maison Espondaburu - Constance Didier", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Manpower", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Manu", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Mapotempo .", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Marie Luce Nantes", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Marie Pilaf", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Marie-Pierre et Jean-Michel Erramoun", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Marion", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Marion", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Marlène Kabengele", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Marmiesse Stephanie", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Mary Claire", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Mathieu", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Mathieu Faivre", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Maxime Fras", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Meetic .", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Meetup", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Melanie Boazman", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Melanie Corr", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Michaël Latour", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Mignon Bernard", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Mirentxu Dabbadie", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Mistur Brown", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Mme Bercu", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Mme Cazala", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Mme Corno", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Mme Mergey", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Mme Rosier", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Mme Theodoly", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Mme Valmary", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Mme Vargues", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Mme Vialard", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Mondial Relay", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Mr Chevalier", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Mr Mergey", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Mr Socquet", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Mr Valton", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Mr. Ropgnl Nev", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("MyScript", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Mêlée Adour", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("NOVARESA", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Nadia Vache", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Nalta Pau Cite Multimédia", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Nathalie Bercu", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Nathalie Domecq", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Nathalie Rosier", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Nicolas PERUSSEL", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Nicolas Peigneguy", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Novae Internet Communication", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Novea", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Numerique 64", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Numlab", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Oihana", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Olivier Patry", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Openbeelab", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Ophtamo Clinique Nantes", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Osmose Emploi", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Oxya France", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Papa", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Paradigm France", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Patrick Menuisier", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Patrick Vagabundo", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Philippe Avi", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Philippe Hingand", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Philippe Lurdos", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Pierre", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Prodware", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Proman, (SAS)", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Psy Alain Alberny", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("QuatorzeHeures", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Quora .", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("REALIS RH - Actual Pau", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("REFLEX INTERIM", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("RUE DES ECOLES", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Rachel Rofé .", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Randstad", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Raphaël Doursenaud", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Raymond et Jeanne Marie", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("Remy", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Remy Pro", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Reni", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Restaurant D'Iraty", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Richy Hélène", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Roberta Bibi", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Ropagnol", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("Ryan Parkins", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Rémy Brousset", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("STEP", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Sabrina", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Samir", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Se-unsa", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Sel Solidaire Pau", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Serge Berry", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Shyirambere Carlos", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Solidarite Nouvelle Face Au Chomage", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Somebody I Know", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Sonja Ger", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Sonja Probst", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Sophie Perisse", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Sos Medecins", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Start People", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Stef2", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Stephane Cnam", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Studio Bleu", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Studios Bleus", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Stéphane Musset", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Stéphanie Freret", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Sunset Code", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("SunsetCode France", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Supplay", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("Synergie", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Sébastien Anox", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("TMS Pau Aquitaine", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Tatiana", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Tatiana", "2130837641", null, null, null, null, "0", "0", "-5319295");
//        addTieUsContactToContactData("Temporis", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Test", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("Test2", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Therese Leroy Martinez Edith", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("Thibault L.", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Thierry Mob", "2130837641", null, null, null, null, "0", "0", "-2825897");
//        addTieUsContactToContactData("Thierry Servetto", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Thierry Taberna", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Titi", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Triangle Interim", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Tribay", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("Tropic Gym", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Twitter .", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("UNIVERSITE DE PAU ET DES PAYS DE L'ADOUR à PAU", "2130837641", null, null, null, null, "0", "0", "-11549705");
//        addTieUsContactToContactData("UneEntreprise", "2130837641", null, null, null, null, "0", "0", "-7297874");
//        addTieUsContactToContactData("Unite De Gestion De La Crise Pau", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Valdete", "2130837641", null, null, null, null, "0", "0", "-18611");
//        addTieUsContactToContactData("Valerie Grinda", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("Veronique Yauri", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Viadeo .", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("Virginie Ospedale Bpso", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Virginie Photo", "2130837641", null, null, null, null, "0", "0", "-11684180");
//        addTieUsContactToContactData("Walter", "2130837641", null, null, null, null, "0", "0", "-8271996");
//        addTieUsContactToContactData("Walter Frajdenberg", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("Work 2000 Formagest", "2130837641", null, null, null, null, "0", "0", "-1739917");
//        addTieUsContactToContactData("Yann Witczak", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("bruno.vasta@free.fr", "2130837641", null, null, null, null, "0", "0", "-8812853");
//        addTieUsContactToContactData("eden Assouline", "2130837641", null, null, null, null, "0", "0", "-6982195");
//        addTieUsContactToContactData("enseignementsup-recherche. gouv. fr", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("fondation Bordeaux Université FBU", "2130837641", null, null, null, null, "0", "0", "-10929");
//        addTieUsContactToContactData("gobiesPalois", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("guillaume resch", "2130837641", null, null, null, null, "0", "0", "-30107");
//        addTieUsContactToContactData("l-informatik", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("marina contis", "2130837641", null, null, null, null, "0", "0", "-1023342");
//        addTieUsContactToContactData("mozart-pme", "2130837641", null, null, null, null, "0", "0", "-4560696");
//        addTieUsContactToContactData("nathalie lerendu-brand", "2130837641", null, null, null, null, "0", "0", "-11677471");
//        addTieUsContactToContactData("nicole maixent", "2130837641", null, null, null, null, "0", "0", "-6190977");
//        addTieUsContactToContactData("sandrine Miegeville", "2130837641", null, null, null, null, "0", "0", "-10177034");
//        addTieUsContactToContactData("Électrique Red", "2130837641", null, null, null, null, "0", "0", "-11684180");


        //I'm following tutorial here part "Adding Events"
        //https://developer.android.com/guide/topics/providers/calendar-provider.html
        String calendarId = selectIdCalendar("Business");
        long dtstart = DateUtils.todayStart();
        long dtend = DateUtils.addHours(1, dtstart);
        String eventTimeZone = TimeZone.getDefault().getID();
        String idEvent = String.valueOf(insertEvent("Audiensiel call", "Workout", calendarId, dtstart, dtend,
                eventTimeZone));
        PlatformUtils.checkAndAskForPermission(this, Manifest.permission.READ_CALENDAR);
        createTableAs("events", mContext.getContentResolver().query(CalendarContract.Events
                .CONTENT_URI, null, CalendarContract.Events._ID + "=?", new String[]{idEvent}, null));

//        //Merge all raw contact into the google raw contact that will sync
//        preparePushContactsTo("com.google", "etchemendy.elorri@gmail.com");

//        //Since it is impossible to write into assets files because apk are packed and therefore
//        // not expandable in size. I will write to external storage to be sure I can access the
//        // file from my phone and send it on drop box or via email.
//        File providersOverview = getFileToWriteIn();
//
//        //List of android builtin content providers static uris that can be queried.
//        //Dynamics uris have been listed here and therefore some provider don't show up (eg
//        // DocumentProvider because it uses only dynamic uris).
//        Object[][] builtInUris = new Object[][]{
//                {"CalendarContract.CONTENT_URI", CalendarContract.CONTENT_URI},
//                {"CalendarContract.CalendarEntity.CONTENT_URI", CalendarContract.CalendarEntity.CONTENT_URI},
//                {"CalendarContract.Calendars.CONTENT_URI", CalendarContract.Calendars.CONTENT_URI},
//                {"CalendarContract.Attendees.CONTENT_URI", CalendarContract.Attendees.CONTENT_URI},
//                {"CalendarContract.EventsEntity.CONTENT_URI", CalendarContract.EventsEntity.CONTENT_URI},
//                {"CalendarContract.Events.CONTENT_URI", CalendarContract.Events.CONTENT_URI},
//                {"CalendarContract.Events.CONTENT_EXCEPTION_URI", CalendarContract.Events.CONTENT_EXCEPTION_URI},
//                {"CalendarContract.Instances.CONTENT_URI", CalendarContract.Instances.CONTENT_URI},
//                {"CalendarContract.Instances.CONTENT_BY_DAY_URI", CalendarContract.Instances.CONTENT_BY_DAY_URI},
//                {"CalendarContract.Instances.CONTENT_SEARCH_URI", CalendarContract.Instances.CONTENT_SEARCH_URI},
//                {"CalendarContract.Instances.CONTENT_SEARCH_BY_DAY_URI", CalendarContract.Instances.CONTENT_SEARCH_BY_DAY_URI},
//                {"CalendarContract.CalendarCache.URI", CalendarContract.CalendarCache.URI},
//                {"CalendarContract.EventDays.CONTENT_URI", CalendarContract.EventDays.CONTENT_URI},
//                {"CalendarContract.Reminders.CONTENT_URI", CalendarContract.Reminders.CONTENT_URI},
//                {"CalendarContract.CalendarAlerts.CONTENT_URI", CalendarContract.CalendarAlerts.CONTENT_URI},
//                {"CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE", CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE},
//                {"CalendarContract.Colors.CONTENT_URI", CalendarContract.Colors.CONTENT_URI},
//                {"CalendarContract.ExtendedProperties.CONTENT_URI", CalendarContract.ExtendedProperties.CONTENT_URI},
//                {"CalendarContract.SyncState.CONTENT_URI", CalendarContract.SyncState.CONTENT_URI},
//                {"CallLog.CONTENT_URI", CallLog.CONTENT_URI},
//                {"CallLog.Calls.CONTENT_URI", CallLog.Calls.CONTENT_URI},
//                {"CallLog.Calls.CONTENT_FILTER_URI", CallLog.Calls.CONTENT_FILTER_URI},
//                {"CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL", CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL},
//                {"ContactsContract.AUTHORITY_URI", ContactsContract.AUTHORITY_URI},
//                {"ContactsContract.Directory.CONTENT_URI", ContactsContract.Directory.CONTENT_URI},
//                {"ContactsContract.SyncState.CONTENT_URI", ContactsContract.SyncState.CONTENT_URI},
//                {"ContactsContract.ProfileSyncState.CONTENT_URI", ContactsContract.ProfileSyncState.CONTENT_URI},
//                {"ContactsContract.Contacts.CONTENT_URI", ContactsContract.Contacts.CONTENT_URI},
//                {"ContactsContract.Contacts.CONTENT_LOOKUP_URI", ContactsContract.Contacts.CONTENT_LOOKUP_URI},
//                {"ContactsContract.Contacts.CONTENT_VCARD_URI", ContactsContract.Contacts.CONTENT_VCARD_URI},
//                {"ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI", ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI},
//                {"ContactsContract.Contacts.CONTENT_FILTER_URI", ContactsContract.Contacts.CONTENT_FILTER_URI},
//                {"ContactsContract.Contacts.CONTENT_STREQUENT_URI", ContactsContract.Contacts.CONTENT_STREQUENT_URI},
//                {"ContactsContract.Contacts.CONTENT_FREQUENT_URI", ContactsContract.Contacts.CONTENT_FREQUENT_URI},
//                {"ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI", ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI},
//                {"ContactsContract.Contacts.CONTENT_GROUP_URI", ContactsContract.Contacts.CONTENT_GROUP_URI},
//                {"ContactsContract.Profile.CONTENT_URI", ContactsContract.Profile.CONTENT_URI},
//                {"ContactsContract.Profile.CONTENT_VCARD_URI", ContactsContract.Profile.CONTENT_VCARD_URI},
//                {"ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI", ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI},
//                {"ContactsContract.DeletedContacts.CONTENT_URI", ContactsContract.DeletedContacts.CONTENT_URI},
//                {"ContactsContract.RawContacts.CONTENT_URI", ContactsContract.RawContacts.CONTENT_URI},
//                {"ContactsContract.Data.CONTENT_URI", ContactsContract.Data.CONTENT_URI},
//                {"ContactsContract.RawContactsEntity.CONTENT_URI", ContactsContract.RawContactsEntity.CONTENT_URI},
//                {"ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI", ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI},
//                {"ContactsContract.PhoneLookup.CONTENT_FILTER_URI", ContactsContract.PhoneLookup.CONTENT_FILTER_URI},
//                {"ContactsContract.StatusUpdates.CONTENT_URI", ContactsContract.StatusUpdates.CONTENT_URI},
//                {"ContactsContract.StatusUpdates.PROFILE_CONTENT_URI", ContactsContract.StatusUpdates.PROFILE_CONTENT_URI},
//                {"ContactsContract.CommonDataKinds.Phone.CONTENT_URI", ContactsContract.CommonDataKinds.Phone.CONTENT_URI},
//                {"ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI},
//                {"ContactsContract.CommonDataKinds.Email.CONTENT_URI", ContactsContract.CommonDataKinds.Email.CONTENT_URI},
//                {"ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI", ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI},
//                {"ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI},
//                {"ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI", ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI},
//                {"ContactsContract.CommonDataKinds.Callable.CONTENT_URI", ContactsContract.CommonDataKinds.Callable.CONTENT_URI},
//                {"ContactsContract.CommonDataKinds.Callable.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Callable.CONTENT_FILTER_URI},
//                {"ContactsContract.CommonDataKinds.Contactables.CONTENT_URI", ContactsContract.CommonDataKinds.Contactables.CONTENT_URI},
//                {"ContactsContract.CommonDataKinds.Contactables.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Contactables.CONTENT_FILTER_URI},
//                {"ContactsContract.Groups.CONTENT_URI", ContactsContract.Groups.CONTENT_URI},
//                {"ContactsContract.Groups.CONTENT_SUMMARY_URI", ContactsContract.Groups.CONTENT_SUMMARY_URI},
//                {"ContactsContract.AggregationExceptions.CONTENT_URI", ContactsContract.AggregationExceptions.CONTENT_URI},
//                {"ContactsContract.Settings.CONTENT_URI", ContactsContract.Settings.CONTENT_URI},
//                {"ContactsContract.DataUsageFeedback.FEEDBACK_URI", ContactsContract.DataUsageFeedback.FEEDBACK_URI},
//                {"ContactsContract.DataUsageFeedback.DELETE_USAGE_URI", ContactsContract.DataUsageFeedback.DELETE_USAGE_URI},
//                {"ContactsContract.DisplayPhoto.CONTENT_URI", ContactsContract.DisplayPhoto.CONTENT_URI},
//                {"ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI", ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI},
//                {"MediaStore.Images.Media.EXTERNAL_CONTENT_URI", MediaStore.Images.Media.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Images.Media.INTERNAL_CONTENT_URI", MediaStore.Images.Media.INTERNAL_CONTENT_URI},
//                {"MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI", MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Images.Thumbnails.INTERNAL_CONTENT_URI", MediaStore.Images.Thumbnails.INTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Media.EXTERNAL_CONTENT_URI", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Media.INTERNAL_CONTENT_URI", MediaStore.Audio.Media.INTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI", MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Genres.INTERNAL_CONTENT_URI", MediaStore.Audio.Genres.INTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI", MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI", MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI", MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Artists.INTERNAL_CONTENT_URI", MediaStore.Audio.Artists.INTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI", MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Audio.Albums.INTERNAL_CONTENT_URI", MediaStore.Audio.Albums.INTERNAL_CONTENT_URI},
//                {"MediaStore.Video.Media.EXTERNAL_CONTENT_URI", MediaStore.Video.Media.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Video.Media.INTERNAL_CONTENT_URI", MediaStore.Video.Media.INTERNAL_CONTENT_URI},
//                {"MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI", MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI},
//                {"MediaStore.Video.Thumbnails.INTERNAL_CONTENT_URI", MediaStore.Video.Thumbnails.INTERNAL_CONTENT_URI},
//                {"Settings.System.CONTENT_URI", Settings.System.CONTENT_URI},
//                {"Settings.System.DEFAULT_ALARM_ALERT_URI", Settings.System.DEFAULT_ALARM_ALERT_URI},
//                {"Settings.System.DEFAULT_NOTIFICATION_URI", Settings.System.DEFAULT_NOTIFICATION_URI},
//                {"Settings.System.DEFAULT_RINGTONE_URI", Settings.System.DEFAULT_RINGTONE_URI},
//                {"Settings.Secure.CONTENT_URI", Settings.Secure.CONTENT_URI},
//                {"Settings.Global.CONTENT_URI", Settings.Global.CONTENT_URI},
//                {"Telephony.Sms.CONTENT_URI", Telephony.Sms.CONTENT_URI},
//                {"Telephony.Sms.Inbox.CONTENT_URI", Telephony.Sms.Inbox.CONTENT_URI},
//                {"Telephony.Sms.Sent.CONTENT_URI", Telephony.Sms.Sent.CONTENT_URI},
//                {"Telephony.Sms.Draft.CONTENT_URI", Telephony.Sms.Draft.CONTENT_URI},
//                {"Telephony.Sms.Outbox.CONTENT_URI", Telephony.Sms.Outbox.CONTENT_URI},
//                {"Telephony.Sms.Conversations.CONTENT_URI", Telephony.Sms.Conversations.CONTENT_URI},
//                {"Telephony.Threads.CONTENT_URI", Telephony.Threads.CONTENT_URI},
//                {"Telephony.Threads.OBSOLETE_THREADS_URI", Telephony.Threads.OBSOLETE_THREADS_URI},
//                {"Telephony.Mms.CONTENT_URI", Telephony.Mms.CONTENT_URI},
//                {"Telephony.Mms.REPORT_REQUEST_URI", Telephony.Mms.REPORT_REQUEST_URI},
//                {"Telephony.Mms.REPORT_STATUS_URI", Telephony.Mms.REPORT_STATUS_URI},
//                {"Telephony.Mms.Inbox.CONTENT_URI", Telephony.Mms.Inbox.CONTENT_URI},
//                {"Telephony.Mms.Sent.CONTENT_URI", Telephony.Mms.Sent.CONTENT_URI},
//                {"Telephony.Mms.Draft.CONTENT_URI", Telephony.Mms.Draft.CONTENT_URI},
//                {"Telephony.Mms.Outbox.CONTENT_URI", Telephony.Mms.Outbox.CONTENT_URI},
//                {"Telephony.Mms.Rate.CONTENT_URI", Telephony.Mms.Rate.CONTENT_URI},
//                {"Telephony.MmsSms.CONTENT_URI", Telephony.MmsSms.CONTENT_URI},
//                {"Telephony.MmsSms.CONTENT_CONVERSATIONS_URI", Telephony.MmsSms.CONTENT_CONVERSATIONS_URI},
//                {"Telephony.MmsSms.CONTENT_FILTER_BYPHONE_URI", Telephony.MmsSms.CONTENT_FILTER_BYPHONE_URI},
//                {"Telephony.MmsSms.CONTENT_UNDELIVERED_URI", Telephony.MmsSms.CONTENT_UNDELIVERED_URI},
//                {"Telephony.MmsSms.CONTENT_DRAFT_URI", Telephony.MmsSms.CONTENT_DRAFT_URI},
//                {"Telephony.MmsSms.CONTENT_LOCKED_URI", Telephony.MmsSms.CONTENT_LOCKED_URI},
//                {"Telephony.MmsSms.SEARCH_URI", Telephony.MmsSms.SEARCH_URI},
//                {"Telephony.MmsSms.PendingMessages.CONTENT_URI", Telephony.MmsSms.PendingMessages.CONTENT_URI},
//                {"Telephony.Carriers.CONTENT_URI", Telephony.Carriers.CONTENT_URI},
//                {"UserDictionary.CONTENT_URI", UserDictionary.CONTENT_URI},
//                {"UserDictionary.Words.CONTENT_URI", UserDictionary.Words.CONTENT_URI},
//                {"VoicemailContract.Voicemails.CONTENT_URI", VoicemailContract.Voicemails.CONTENT_URI},
//                {"VoicemailContract.Status.CONTENT_URI", VoicemailContract.Status.CONTENT_URI}
//        };
//
//
//        String[] builtInProviderDesc = describeUris(this, builtInUris);
//        writeTableOfStringToFile(providersOverview, builtInProviderDesc);


        setContentView(R.layout.activity_main);
    }

    private void removeTieUsContactFromContactData() {
        //Because I have finally decided to not use android contact provider to store my tie us
        // contacts (Because anyway I won't be able to store profile data), I therefore remove
        // what I have peviously added.
        ArrayList<ContentProviderOperation> cpo=new ArrayList<>();
        cpo.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI)
        .withSelection(ContactsContract.Data.MIMETYPE + "=?", new
                String[]{TieUsContract.Contact.CONTENT_ITEM_TYPE})
                .build());
        try{
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, cpo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private void addTieUsActionsToProfileData() {
        String[] contactInfos = gettingTheProfileRawcontactIdAndLookUp();
        String contactId = contactInfos[0];
        String lookup = contactInfos[1];

        //Only for test purposes. As we can see data_id and contact_id are identical. I guess the
        // key is based on mimetype+data1 ?
        Cursor rawContactsDataFull = selectProfileDataFull();
        createTableAs("rawContactsDataFullTable", rawContactsDataFull);

        //Try to add a profile with account type and account name google
        //I get an java.lang.UnsupportedOperationException: URI: content://com.android.contacts/profile/raw_contact_entities
        //Ccl : I won't be able to add data on built in profile provider. Will only be able to
        // read it.
        //Profile data is stored only on the phone peripherique. Seems its impossible to store it
        // in a syncable account like google+. Therefore this data won't be saved on a distant
        // serveur.
        // Other note : Even if I can store data using my own mimetypes inside RawContact.Data I
        // can't be completely sure that this data will be stored on Google+. There is big
        // chances that google+ server only store the mimetypes he is able to read.
        // For those 2 reasons I will have to create my own backend server.
        // I can't insert in profile data but should i insert in contact data (because this is
        // possible) ??
        // I think the best is
        // - insert in contact data only mimetypes known by android and therefore other app. As off
        // now tieus has no reasons to insert android mimetypes. Lets others app do this. But it
        // can insert events.  In brief : anything that can be understood by android content
        // provider should be pushed in them, anything that can't be understood by android content
        // provider shouldn't be pushed in them
        // - during each sync copy data from android content providers into your own. Taking only
        // data that interest us.
        // - push data added in our content provider (data added during the use of the app + data
        // from android content provider now stored in our cp) to our backend.
        // In fact from what is already existing I have to reorganised a little bit my tables to
        // make them easily stored in a json file
        addGoogleUserProfile(contactId, "com.google", "etchemendy.elorri@gmail.com");

        //Only for test purposes.
        rawContactsDataFull = selectProfileDataFull();
        createTableAs("rawContactsDataFullTable", rawContactsDataFull);

    }

    private void addGoogleUserProfile(String contactId, String accountType, String accountName) {

        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI)
                .withValue(ContactsContract.RawContacts.CONTACT_ID, contactId)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, accountType)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, accountName)
                .build());
        try {
            //PlatformUtils.checkAndAskForPermission(this, Manifest.permission.WRITE_PROFILE, PlatformUtils.PERMISSION_WRITE_CONTACTS);
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    private Cursor selectProfileDataFull() {
        String[] projection = new String[]{ContactsContract.Data._ID,
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.RawContacts.ACCOUNT_TYPE,
                ContactsContract.RawContacts.ACCOUNT_NAME,
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    private void addTieUsContactToContactData(String contactName,
                                              String satisfaction,
                                              String expectedResponseTimeLimit,
                                              String increasedExpectedResponseTimeLimit,
                                              String frequencyOfContact,
                                              String lastSatisfactionUpdate,
                                              String unfollowed,
                                              String satisfactionUnknown,
                                              String backgroundColor) {

        Log.e("Contact", Thread.currentThread().getStackTrace()[2] + "contactName : " + contactName);

        String[] contactInfos = gettingTheMergeContactIdAndLookUp(contactName);
        String contactId = contactInfos[0];
        String lookup = contactInfos[1];
        String rawContactId = gettingFirstRawContactIdGivenForSync(contactId, "com.google", "etchemendy.elorri@gmail.com");

        //Only for test purposes. I commrnt this because trying copying big fields like field
        // android mimetype note data1 can result in sqlexception
//        Cursor rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
//        createTableAs("rawContactsDataFullTable", rawContactsDataFull);

//        //Delete previously added rows
//        mContext.getContentResolver().delete(
//                ContactsContract.Data.CONTENT_URI,
//                ContactsContract.Data.RAW_CONTACT_ID + "=? and " +
//                        ContactsContract.Data.MIMETYPE + "=?",
//                new String[]{rawContactId, TieUsContract.Contact.CONTENT_ITEM_TYPE});


        //Check if tieus contact data has already been added
        Cursor cursor = mContext.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.RAW_CONTACT_ID + "=? and " +
                        ContactsContract.Data.MIMETYPE + "=?",
                new String[]{convertToNullString(rawContactId),
                        convertToNullString(TieUsContract.Contact.CONTENT_ITEM_TYPE)},
                null);
        if (cursor.getCount() > 0) {
            //raw has already been added. Don't do it again.

            //Only for test purposes
//            rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
//            createTableAs("rawContactsDataFullTableAfter", rawContactsDataFull);
            return;
        }
        cursor.close();

        //Add tieus contact data
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValue(ContactsContract.Data.RAW_CONTACT_ID, Integer.parseInt(rawContactId))
                .withValue(ContactsContract.Data.MIMETYPE, TieUsContract.Contact.CONTENT_ITEM_TYPE)
                .withValue(TieUsContract.Contact.CONTACT_ID, contactId)
                .withValue(TieUsContract.Contact.LOOKUP_KEY, lookup)
                .withValue(TieUsContract.Contact.SATISFACTION, satisfaction)
                .withValue(TieUsContract.Contact.RESPONSE_EXPECTED_TIME_LIMIT, expectedResponseTimeLimit)
                .withValue(TieUsContract.Contact.RESPONSE_INCREASED_EXPECTED_TIME_LIMIT, increasedExpectedResponseTimeLimit)
                .withValue(TieUsContract.Contact.FREQUENCY_OF_CONTACT, frequencyOfContact)
                .withValue(TieUsContract.Contact.LAST_SATISFACTION_DECREASED, lastSatisfactionUpdate)
                .withValue(TieUsContract.Contact.UNFOLLOWED, unfollowed)
                .withValue(TieUsContract.Contact.SATISFACTION_UNKNOWN, satisfactionUnknown)
                .withValue(TieUsContract.Contact.BACKGROUND_COLOR, backgroundColor)
                .build());
        try {
            PlatformUtils.checkAndAskForPermission(this, Manifest.permission.WRITE_CONTACTS,
                    PlatformUtils.PERMISSION_WRITE_CONTACTS);
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Only for test purposes
//        rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
//        createTableAs("rawContactsDataFullTableAfter", rawContactsDataFull);

    }

    private String getIsNullOrEqualsBind(String value) {
        if (value == null) {
            return "is ?";
        }
        return "=?";
    }

    private String gettingFirstRawContactIdGivenForSync(String contactId, String accountType, String accountName) {
        //Select the first raw contact with account name given for synchro
        Cursor rawContactsGivenForSync = selectFirstRawContactGivenForAccountSync(contactId, accountType, accountName);
        rawContactsGivenForSync.moveToFirst();
        return rawContactsGivenForSync.getString(rawContactsGivenForSync.getColumnIndex(ContactsContract.RawContacts._ID));
    }

    /**
     * This method will browse all contacts in the contact provider and read all it's related raw
     * contacts and merge all raw contact data into one signe raw contact that can be
     * synchronized with serveur using  accountType and accountName given.
     *
     * @param accountType for sync.
     * @param accountName for sync.
     */
    private void preparePushContactsTo(String accountType, String accountName) {
        String contactId = gettingTheMergeContactIdAndLookUp("1Contact%")[0];
        preparePushContactTo(contactId, accountType, accountName);
    }

    private void preparePushContactTo(String contactId, String accountType, String accountName) {
        //Select the first raw contact with account name given for synchro
        Cursor rawContactsGivenForSync = selectFirstRawContactGivenForAccountSync(contactId, accountType, accountName);
        rawContactsGivenForSync.moveToFirst();
        String rawContactIdGivenForSync = rawContactsGivenForSync.getString(rawContactsGivenForSync
                .getColumnIndex(ContactsContract.RawContacts._ID));

        //Only for test purposes
        Cursor rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
        createTableAs("rawContactsDataFullTable", rawContactsDataFull);

        //Select all stored data for this given contact
        Cursor rawContactsData = selectSyncRawContactsMimetypeDatas(contactId);
        String rawContactsDataTable = "rawContactsDataTable";
        createTableAs(rawContactsDataTable, rawContactsData);

        //Select all stored data for raw contact associated with the account type
        Cursor rawContactData = selectSyncRawContactMimetypeDatas(rawContactIdGivenForSync);
        String rawContactDataTable = "rawContactDataTable";
        createTableAs(rawContactDataTable, rawContactData);

        //Because we want to select a distinct of each table
        rawContactsData = distinctTable(rawContactsDataTable);
        rawContactData = distinctTable(rawContactDataTable);
        createTableAs(rawContactsDataTable, rawContactsData);
        createTableAs(rawContactDataTable, rawContactData);

        //Remove the data stored in the raw contact associated with the account type
        // (rawContactsDataString - rawContactDataString)
        Cursor cursorToCompleteSynRawContact = minus(rawContactsDataTable, rawContactDataTable);
        createTableAs("minus", cursorToCompleteSynRawContact);
        cursorToCompleteSynRawContact.moveToPosition(-1);

        //Add infos to rawContactGivenForSync
        completeRawContactGivenForSync(rawContactIdGivenForSync, cursorToCompleteSynRawContact);

        //Remove other remaining rawContactForSync
        removeOtherRawContactGivenForSync(rawContactsGivenForSync);

        //Only for checking delete has worked properly during tests
        //Only for test purposes
        rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
        createTableAs("rawContactsDataFullTableAfter", rawContactsDataFull);

        //Select all stored data for this given contact
        rawContactsData = selectSyncRawContactsMimetypeDatas(contactId);
        createTableAs("rawContactsDataTableAfter", rawContactsData);

        //Close cursors and delete now useless tables


    }

    private void removeOtherRawContactGivenForSync(Cursor rawContactsGivenForSync) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        while (rawContactsGivenForSync.moveToNext()) {
            String rawContactIdGivenForSync = rawContactsGivenForSync.getString
                    (rawContactsGivenForSync
                            .getColumnIndex(ContactsContract.RawContacts._ID));
            ops.add(ContentProviderOperation.newDelete(
                    ContactsContract.RawContacts.CONTENT_URI)
                    .withSelection(ContactsContract.RawContacts._ID + " = ?", new String[]{rawContactIdGivenForSync})
                    .build());
        }

        try {
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String selectIdCalendar(String name) {
        mCalendarName = name;
        PlatformUtils.checkAndAskForPermission(this, Manifest.permission.READ_CALENDAR,
                PlatformUtils.PERMISSION_READ_CALENDAR);
        Cursor cursor = mContext.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI,
                new String[]{CalendarContract.Calendars._ID}, CalendarContract.Calendars.NAME + " " +
                        "=?", new String[]{name}, null);
        cursor.moveToNext();
        return cursor.getString(0);
    }

    private Long insertEvent(String title, String description, String calendarId, long dtstart,
                             long dtend, String eventTimeZone) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newInsert(
                CalendarContract.Events.CONTENT_URI)
                .withValue(CalendarContract.Events.CALENDAR_ID, calendarId)
                .withValue(CalendarContract.Events.DTSTART, dtstart)
                .withValue(CalendarContract.Events.DTEND, dtend)
                .withValue(CalendarContract.Events.EVENT_TIMEZONE, eventTimeZone)
                .withValue(CalendarContract.Events.TITLE, title)
                .withValue(CalendarContract.Events.DESCRIPTION, description)
                .build());

        try {
            //The ContentResolver.applyBatch() method returns an array of ContentProviderResult objects, one for each operation.
            //Each of these has the uri of the inserted contact (in the format content://com.android.contacts/raw_contacts/<contact_id>).
            ContentProviderResult[] res = mContext.getContentResolver().applyBatch(CalendarContract.AUTHORITY, ops);
            return ContentUris.parseId(res[0].uri);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }


    }

    private void completeRawContactGivenForSync(String rawContactId, Cursor cursorWithRawContactInfos) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        while (cursorWithRawContactInfos.moveToNext()) {
            opsAddRawContactData(ops, rawContactId, cursorWithRawContactInfos);
        }
        try {
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void opsAddRawContactData(ArrayList<ContentProviderOperation> ops, String rawContactId, Cursor cursorWithawContactInfos) {
        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValue(ContactsContract.Data.RAW_CONTACT_ID, Integer.parseInt(rawContactId))
                .withValue(ContactsContract.Data.MIMETYPE,
                        cursorWithawContactInfos.getString(cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.MIMETYPE)))
                .withValue(ContactsContract.Data.DATA1,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA1))))
                .withValue(ContactsContract.Data.DATA2,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA2))))
                .withValue(ContactsContract.Data.DATA3,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA3))))
                .withValue(ContactsContract.Data.DATA4,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA4))))
                .withValue(ContactsContract.Data.DATA5,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA5))))
                .withValue(ContactsContract.Data.DATA6,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA6))))
                .withValue(ContactsContract.Data.DATA7,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA7))))
                .withValue(ContactsContract.Data.DATA8,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA8))))
                .withValue(ContactsContract.Data.DATA9,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA9))))
                .withValue(ContactsContract.Data.DATA11,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA11))))
                .withValue(ContactsContract.Data.DATA10,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA10))))
                .withValue(ContactsContract.Data.DATA12,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA12))))
                .withValue(ContactsContract.Data.DATA13,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA13))))
                .withValue(ContactsContract.Data.DATA14,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA14))))
                .withValue(ContactsContract.Data.DATA15,
                        convertToNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA15))))
                .build());
    }

    private String convertToNull(String string) {
        if (string.equals("null")) {
            return null;
        }
        return string;
    }

    private String convertToNullString(String string) {
        if (string == null) {
            return "null";
        }
        return string;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PlatformUtils.PERMISSION_READ_CALENDAR: {
                selectIdCalendar(mCalendarName);
                return;
            }
            case PlatformUtils.PERMISSIONS_REQUEST_WRITE_CONTACTS: {
                addTieUsContactToContactData(mContactName, mSatisfaction, mExpectedResponseTimeLimit,
                        mIncreasedExpectedResponseTimeLimit, mFrequencyOfContact, mLastSatisfactionUpdate,
                        mUnfollowed, mSatisfactionUnknown, mBackgroundColor);
                return;
            }
        }
    }

    private Cursor distinctTable(String name) {
        return db.query(true, name, null, null, null, null, null, null, null);
    }

    private void createTableAs(String name, Cursor cursor) {
        String cursorCreateString = createTableAsString(name, cursor);
        if (cursorCreateString == null) {
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " + name);
        db.execSQL(cursorCreateString);
    }

    private Cursor minus(String tableA, String tableB) {
        //In short
        //SELECT A_label as label FROM (select A.label as A_label, B.label as B_label from rawContactsDataTable A left outer join rawContactDataTable B on A.label=B.label) WHERE B_label is null)
        String table = "(select " +
                "A.mimetype as A_mimetype, " +
                "A.data1 as A_data1, " +
                "A.data2 as A_data2, " +
                "A.data3 as A_data3, " +
                "A.data4 as A_data4, " +
                "A.data5 as A_data5, " +
                "A.data6 as A_data6, " +
                "A.data7 as A_data7, " +
                "A.data8 as A_data8, " +
                "A.data9 as A_data9, " +
                "A.data10 as A_data10, " +
                "A.data11 as A_data11, " +
                "A.data12 as A_data12, " +
                "A.data13 as A_data13, " +
                "A.data14 as A_data14, " +
                "A.data15 as A_data15, " +
                "B.mimetype as B_mimetype " +
                "from " + tableA + " A left outer join " + tableB + " B " +
                "on A.mimetype=B.mimetype " +
                "and A.data1=B.data1 " +
                "and A.data2=B.data2 " +
                "and A.data3=B.data3 " +
                "and A.data4=B.data4 " +
                "and A.data5=B.data5 " +
                "and A.data6=B.data6 " +
                "and A.data7=B.data7 " +
                "and A.data8=B.data8 " +
                "and A.data9=B.data9 " +
                "and A.data10=B.data10 " +
                "and A.data11=B.data11 " +
                "and A.data12=B.data12 " +
                "and A.data13=B.data13 " +
                "and A.data14=B.data14 " +
                "and A.data15=B.data15" +
                ")";
        String[] projection = new String[]{"A_mimetype as mimetype, " +
                "A_data1 as data1, " +
                "A_data2 as data2, " +
                "A_data3 as data3, " +
                "A_data4 as data4, " +
                "A_data5 as data5, " +
                "A_data6 as data6, " +
                "A_data7 as data7, " +
                "A_data8 as data8, " +
                "A_data9 as data9, " +
                "A_data10 as data10, " +
                "A_data11 as data11, " +
                "A_data12 as data12, " +
                "A_data13 as data13, " +
                "A_data14 as data14, " +
                "A_data15 as data15"};
        String selection = "B_mimetype is null";
        return db.query(table, projection, selection, null, null, null, null);
    }


    private String createTableAsString(String name, Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        int rowCount = cursor.getCount();
        if (rowCount < 1) {
            return null;
        }
        String create = "create table " + name + " as ";
        cursor.moveToPosition(-1);
        int i = 0;
        while (cursor.moveToNext()) {
            create += "select ";
            int j = 0;
            for (String column : cursor.getColumnNames()) {
                create += "'" + cursor.getString(j) + "' as " + column;
                if (j < columnCount - 1) {
                    create += ", ";
                }
                j++;
            }
            if (i < rowCount - 1) {
                create += " union ";
            }
            i++;
        }
        create += "";

        return create;
    }

    private Cursor selectSyncRawContactsMimetypeDataFull(String contactId) {
        String[] projection = new String[]{ContactsContract.Data._ID,
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.Data.LOOKUP_KEY,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.RawContacts.ACCOUNT_TYPE,
                ContactsContract.RawContacts.ACCOUNT_NAME,
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                projection,
                ContactsContract.Data.CONTACT_ID + " = ?",
                new String[]{contactId},
                ContactsContract.Data.CONTACT_ID + " asc");
    }

    private Cursor selectSyncRawContactMimetypeDatas(String rawContactId) {
        String[] projection = new String[]{
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                projection,
                ContactsContract.Data.RAW_CONTACT_ID + " = ?",
                new String[]{rawContactId},
                null);
    }

    private String[][] convertToTable(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        int rowCount = cursor.getCount();

        String[][] table = new String[rowCount][columnCount];
        int i = 0;
        while (cursor.moveToNext()) {
            for (int j = 0; j < columnCount; j++) {
                table[i][j] = cursor.getString(j);
            }
            i++;
        }

        return table;
    }

    private Cursor selectSyncRawContactsMimetypeDatas(String contactId) {
        String[] projection = new String[]{
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                projection,
                ContactsContract.Data.CONTACT_ID + " = ?",
                new String[]{contactId},
                null);
    }

    private Cursor selectFirstRawContactGivenForAccountSync(String contactId,
                                                            String accountType, String accountName) {
        String[] projection = new String[]{ContactsContract.RawContacts._ID,
                ContactsContract.RawContacts.CONTACT_ID,
                ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,
                ContactsContract.RawContacts.ACCOUNT_TYPE,
                ContactsContract.RawContacts.ACCOUNT_NAME};
        return mContext.getContentResolver().query(
                ContactsContract.RawContacts.CONTENT_URI,
                projection,
                ContactsContract.RawContacts.CONTACT_ID + " = ? and " +
                        ContactsContract.RawContacts.ACCOUNT_TYPE + " = ? and " +
                        ContactsContract.RawContacts.ACCOUNT_NAME + " = ? ",
                new String[]{contactId, accountType, accountName},
                ContactsContract.RawContacts.CONTACT_ID + " asc");
    }


    private String[] gettingTheMergeContactIdAndLookUp(String contactName) {
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    new String[]{ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.LOOKUP_KEY,
                            ContactsContract.Contacts.DISPLAY_NAME},
                    ContactsContract.Contacts.DISPLAY_NAME + " like ?",
                    new String[]{contactName},
                    ContactsContract.Contacts.DISPLAY_NAME + " asc");
            if (cursor.getCount() == 0 || cursor.getCount() > 1) {
                Log.e("Contact", Thread.currentThread().getStackTrace()[2] + "Could not found " +
                        "contactId for contact " + contactName);
            }
            cursor.moveToFirst();
            return new String[]{cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY))};
        } catch (Exception e) {
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String describeCursor(Cursor cursor) {
        //On the first line print the columns names
        String desc = describeColumns(cursor);
        desc += describeRows(cursor);
        return desc;
    }

    private String describeRows(Cursor cursor) {
        String desc = "";
        while (cursor.moveToNext()) {
            desc += describeRow(cursor);
            //We should make sure our cursor is filtered and does not have too musch rows.
        }
        return desc;
    }

    private String describeRow(Cursor cursor) {
        String desc = "";
        String[] columnsNames = cursor.getColumnNames();
        int columnCount = columnsNames.length;
        int i = 0;
        for (String columnName : columnsNames) {
            desc += cursor.getString(cursor.getColumnIndex(columnName));
            if (i == columnCount - 1) {
                desc += "\n";
            } else {
                desc += "|";
            }
            i++;
        }
        return desc;
    }

    private String describeColumns(Cursor cursor) {
        String desc = "";
        String[] columnsNames = cursor.getColumnNames();
        int columnCount = columnsNames.length;
        int i = 0;
        for (String columnName : columnsNames) {
            desc += columnName;
            if (i == columnCount - 1) {
                desc += "\n";
            } else {
                desc += "|";
            }
            i++;
        }
        return desc;
    }

    private void writeTableOfStringToFile(File providersOverview, String[] strings) {
        try (FileWriter fw = new FileWriter(providersOverview, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String aString : strings) {
                out.println(aString);
                out.println("\n");
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public File getFileToWriteIn() {
        File providersOverviewDir = FileUtils.getPublicAppDir(this, "AppDir");
        FileUtils.deleteFiles(providersOverviewDir);
        return FileUtils.createFile(providersOverviewDir, new File
                (providersOverviewDir, "providersOverview.txt"));
    }

    public String[] gettingTheProfileRawcontactIdAndLookUp() {
        // By calling ... we can note :
        // ContactsContract.Profile.CONTENT_URI - content://com.android.contacts/profile
        // ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI - content://com.android.contacts/profile/raw_contacts
        // Seems that contactId and rawContactId are the same in boths tables (Profile and
        // RawContact). There is no foreign key contact_id or raw_contact_id in both tables. Both
        // tables _id refers to contactId and rawContactId.
        // I choose to call 'profile' uri because it also gives the lookup key.
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(ContactsContract.Profile.CONTENT_URI,
                    new String[]{ContactsContract.Profile._ID, ContactsContract.Profile.LOOKUP_KEY}, null, null,
                    null);
            cursor.moveToNext();
            return new String[]{cursor.getString(0), cursor.getString(1)};
        } finally {
            cursor.close();
        }
    }
}
