package com.anjilibey.qrcode.kelas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anjilibey.qrcode.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment{
View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.activity_about_fragment, container, false);

        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");
        
        View aboutPage = new AboutPage(getActivity())
                .isRTL(false)
                .setImage(R.drawable.ugm_baru)
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .setDescription("Aplikasi absensi untuk mahasiswa sekolah vokasi UGM")
                .addGroup("Connect with us")
                .addEmail("anjilibey@gmail.com")
                .addWebsite("http://sv.ugm.ac.id")
                .addFacebook("Luthfi9.tabey")
                .addTwitter("LuthfiTabey")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addInstagram("luthfitabey")
                .addGitHub("luthfitabey")
                .addItem(getCopyRightsElement())
                .create();

            return aboutPage;
        }

    private Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = "Sekolah Vokasi UGM @2018";
        copyRightsElement.setTitle(copyrights);
//        copyRightsElement.setIconDrawable(R.drawable.ugm_gg);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }

}

