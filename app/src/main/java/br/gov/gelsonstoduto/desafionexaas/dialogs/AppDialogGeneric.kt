package br.gov.gelsonstoduto.desafionexaas.dialogs

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import br.gov.gelsonstoduto.desafionexaas.R
import kotlinx.android.synthetic.main.dialog_generic_app.*

class AppDialogGeneric(private val tvMensagem: String, private val tvDetalhe: String) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_generic_app, container, false)
    }

    private fun setListeners() {
        btFechar.setOnClickListener { activity?.finish() }
    }

    private fun initViews() {
        tvMensagemGeneric.text = tvMensagem
        tvMensagemDetalhe.text = tvDetalhe
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setListeners()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window
            ?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.blackTitle)))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onResume() {
        super.onResume()
        dialog!!.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

}