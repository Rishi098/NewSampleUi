package com.itamazons.whatsdp.ui.home

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
import com.itamazons.whatsdp.WhatsBackgroundAdapter
import com.itamazons.whatsdp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var ImageList:ArrayList<String>
    lateinit var imageurl:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ImageList = ArrayList()
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        for (i in 1..9){
            imageurl = "https://www.setaswall.com/wp-content/uploads/2019/08/Whatsapp-Chat-Wallpaper-00${i}-340x550.jpg"
            ImageList.add(imageurl)
            imageurl = "https://www.setaswall.com/wp-content/uploads/2019/08/Whatsapp-Wallpaper-00${i}-768x1365.jpg"
            ImageList.add(imageurl)
        }

        for (i in 10..99){
            imageurl = "https://www.setaswall.com/wp-content/uploads/2019/08/Whatsapp-Chat-Wallpaper-0${i}-340x550.jpg"
            ImageList.add(imageurl)
            imageurl = "https://www.setaswall.com/wp-content/uploads/2019/08/Whatsapp-Wallpaper-0${i}-768x1365.jpg"
            ImageList.add(imageurl)
        }

        for (i in 100..268){
            imageurl = "https://www.setaswall.com/wp-content/uploads/2019/08/Whatsapp-Chat-Wallpaper-${i}-340x550.jpg"
            ImageList.add(imageurl)
            if (i<123){
                imageurl = "https://www.setaswall.com/wp-content/uploads/2019/08/Whatsapp-Wallpaper-${i}-768x1365.jpg"
                ImageList.add(imageurl)
            }
        }

        binding.recyclerView.adapter = WhatsBackgroundAdapter(ImageList)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setHasFixedSize(true)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}