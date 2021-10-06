package com.itamazons.whatsdp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.itamazons.whatsdp.R
import com.itamazons.whatsdp.WhatsDpAdapter
import com.itamazons.whatsdp.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    lateinit var ImageList: ArrayList<String>
    lateinit var imageurl: String
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ImageList = ArrayList()
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        ImageList = ArrayList()

        for (i in 1..55) {

            imageurl =
                "https://i1.wp.com/www.kahanihindi.com/wp-content/uploads/2021/01/sad-girl-dp-profile-${i}.jpg?w=450&ssl=1"
            ImageList.add(imageurl)

            if (i < 26) {
                imageurl =
                    "https://i1.wp.com/www.kahanihindi.com/wp-content/uploads/2020/02/attitude-girls-DPz-${i}.jpg?resize=500%2C500&ssl=1"
                ImageList.add(imageurl)

                imageurl =
                    "https://i0.wp.com/www.kahanihindi.com/wp-content/uploads/2020/02/sweet-dp-profile-${i}.jpg?resize=500%2C500&ssl=1"
                ImageList.add(imageurl)
            }

            if (1 < 41) {
                imageurl =
                    "https://i1.wp.com/www.kahanihindi.com/wp-content/uploads/2020/12/CARTOON-DP-PROFILE-${i}.jpg?resize=500%2C500&ssl=1"
                ImageList.add(imageurl)
            }

            if (i < 51) {
                imageurl =
                    "https://i0.wp.com/www.kahanihindi.com/wp-content/uploads/2021/02/cute-love-pics-for-whatsapp-profile-${i}.jpg?w=450&ssl=1"
                ImageList.add(imageurl)
            }
        }



        binding.recyclerView.adapter = WhatsDpAdapter(ImageList)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setHasFixedSize(true)



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}