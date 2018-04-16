package pucaberta.pucminas.com.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tbruyelle.rxpermissions.RxPermissions;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseFragmentViewModel;
import pucaberta.pucminas.com.databinding.FragmentMapsBinding;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.viewmodel.MapsFragmentViewModel;

import static pucaberta.pucminas.com.app.Constants.COURSE;

/**
 * Created by lucas on 09/04/2018.
 * Update at 09/04/2018
 */

public class MapsFragment extends BaseFragmentViewModel<FragmentMapsBinding, MapsFragmentViewModel> implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false);
        mViewModel = new MapsFragmentViewModel(this);
        mBinding.setViewModel(mViewModel);

        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);




        mBinding.switchCantina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                addMarcadoresCantinas(checked);

            }
        });

        return mBinding.getRoot();
    }

    @SuppressLint("MissingPermission")
    private void checkPermission() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        mMap.setMyLocationEnabled(true);
                    } else {
                        // At least one permission is denied
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng receptivo = new LatLng(-19.924542, -43.993056);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setBuildingsEnabled(true);

        mMap.isIndoorEnabled();

        mMap.getUiSettings().setZoomControlsEnabled(true);

        CameraPosition mCameraPosition = CameraPosition.builder(mMap.getCameraPosition())
                .target(receptivo)
                .bearing(310)
                .zoom(17)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));

        checkPermission();

        mMap.setOnMyLocationButtonClickListener(() -> {

            if (!Utils.isGPSEnabled()) {
                Toast.makeText(getActivity(), "GPS está desabilitado, favor habilitar para que seja possível orientá-lo no campus", Toast.LENGTH_SHORT).show();
            }

            return false;
        });

        addMarcadoresPrincipais();

    }


    private void addMarcadoresPrincipais() {

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.924542, -43.993056)).title("Receptivo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_receptivo))

        ).showInfoWindow();


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922306, -43.993384))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_feira_de_cursos))

        );

        if (!TextUtils.isEmpty(getClickedCourse())) {

            if (getClickedCourse().contains("Grupo A")) {
                addMarcadoresGrupoA();
            }
            if (getClickedCourse().contains("Grupo B")) {
                addMarcadoresGrupoB();
            }
            if (getClickedCourse().contains("Grupo C")) {
                addMarcadoresGrupoC();
            }
            if (getClickedCourse().contains("Grupo D")) {
                addMarcadoresGrupoD();
            }
            if (getClickedCourse().contains("Grupo E")){
                addMarcadoresGrupoE();
            }
            if (getClickedCourse().contains("Grupo F")) {
                addMarcadoresGrupoF();
            }
            if (getClickedCourse().contains("Grupo G")) {
                addMarcadoresGrupoG();
            }
            if (getClickedCourse().contains("Grupo H")) {
                addMarcadoresGrupoH();
            }
            if (getClickedCourse().contains("Grupo I")) {
                addMarcadoresGrupoI();
            }
            if (getClickedCourse().contains("Grupo J")) {
                addMarcadoresGrupoJ();
            }

        }
    }

    private void addMarcadoresCantinas(boolean visible) {


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923973, -43.993499)).title("Cantina Sinhazinha")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cantinas))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923878, -43.994025))
                .title("Trailer de Lanches")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cantinas))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923375, -43.993772))
                .title("Cantina Divin Gout")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cantinas))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922603, -43.992991))
                .title("Cantina Shuffner")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cantinas))
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923024, -43.991382))
                .title("Cantina Sodexo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cantinas))
        );

        if (!visible) {
            mMap.clear();
            addMarcadoresPrincipais();
        }

    }

    private String getClickedCourse(){
        return PucApp.prefs.getString(COURSE);
    }

    private void addMarcadoresGrupoA() {

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923171, -43.991035))
                .title("Grupo A - 13h30 às 15h30 - Teatro João Paulo II")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))

        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.924144, -43.99296))
                .title("Instituto Politécnico - IPUC ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoB() {

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923265, -43.992904))
                .title("Grupo B - 13h30 às 15h30 - Auditório I")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922958, -43.992619))
                .title("Faculdade Mineira de Direito")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoC() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922958, -43.992619))
                .title("Grupo C - 13h30 às 15h30 - Auditório II")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922651, -43.991751))
                .title("Instituto de Ciências Econômicas e Gerenciais - ICEG")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoD() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923527, -43.99335))
                .title("Grupo D - 13h30 às 15h30 - Auditório III")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.924401, -43.994458))
                .title("Instituto de Ciências Biológicas e da Saúde - ICBS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoE() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923527, -43.993354))
                .title("Grupo E - 13h30 às 15h30 - Auditório Multiuso")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.92308, -43.992009))
                .title("Instituto de Ciências Humanas - ICH")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoF() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923171, -43.991035))
                .title("Grupo F - 16h às 18h - Teatro João Paulo II")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.924401, -43.994458))
                .title("Instituto de Ciências Biológicas e da Saúde - ICBS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoG() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923265, -43.992904))
                .title("Grupo G - 16h às 18h - Auditório I")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922651, -43.992424))
                .title("Faculdade de Psicologia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoH() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922958, -43.992619))
                .title("Grupo H - 16h às 18h - Auditório II")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.922442, -43.992665))
                .title("Faculdade de Comunicação e Artes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoI() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923527, -43.993354))
                .title("Grupo I - 16h às 18h - Auditório III")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.925006, -43.993402))
                .title("Instituto de Ciências Sociais - ICS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }

    private void addMarcadoresGrupoJ() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923527, -43.993354))
                .title("Grupo J - 16h às 18h - Auditório Multiuso")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_auditorios))
        ).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-19.923045, -43.994409))
                .title("Instituto de Ciências Exatas e Informática - ICEI")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_institutos_e_faculdades))
        );
    }
}