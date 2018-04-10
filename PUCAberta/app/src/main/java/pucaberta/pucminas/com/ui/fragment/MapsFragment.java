package pucaberta.pucminas.com.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseFragmentViewModel;
import pucaberta.pucminas.com.databinding.FragmentMapsBinding;
import pucaberta.pucminas.com.viewmodel.MapsFragmentViewModel;

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
}