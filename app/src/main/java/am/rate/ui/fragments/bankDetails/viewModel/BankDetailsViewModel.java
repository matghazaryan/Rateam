package am.rate.ui.fragments.bankDetails.viewModel;

import android.app.Application;

import com.dm.dmnetworking.DMNetworkLiveDataBag;
import com.dm.dmnetworking.model.error.ErrorE;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import am.rate.application.RateApplication;
import am.rate.core.model.BankData;
import am.rate.core.model.Branch;
import am.rate.core.model.RequestError;
import am.rate.core.model.WorkDay;
import am.rate.core.respository.DataRepository;
import am.rate.ui.fragments.base.base.DMBaseIError;
import am.rate.ui.fragments.base.base.DMBaseViewModel;
import androidx.annotation.NonNull;

public class BankDetailsViewModel extends DMBaseViewModel<RateApplication> {

    public BankData bankData;

    public BankDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public DMNetworkLiveDataBag<Branch, RequestError> apiBankDetails(String guid) {
        return DataRepository.api().bankDetails(mContext,guid);
    }

    @Override
    protected <ErrorRequest extends DMBaseIError> void handleErrors(final ErrorE<ErrorRequest> errorE) {
        super.handleErrors(errorE);
    }

    public BankData getParsedBranchData(JSONObject successJSONObject) {
        List<Branch> branchList = new ArrayList<>();
        Branch headBranch = new Branch();

        try {
            JSONObject json = successJSONObject.getJSONObject("list");
            for (int i = 0; i < json.names().length(); i++) {
                Branch branch = new Branch();
                String guid = successJSONObject.getJSONObject("list").names().getString(i);
                JSONObject listJSON = successJSONObject.getJSONObject("list").getJSONObject(guid);
                branch.setHead(listJSON.getInt("head"));
                branch.setContacts(listJSON.getString("contacts"));

                JSONObject jsonObjectTitle = listJSON.getJSONObject("title");
                branch.setTitle(jsonObjectTitle.getString(Lang.currentLang));

                JSONObject jsonObjectAddress = listJSON.getJSONObject("address");
                branch.setAddress(jsonObjectAddress.getString(Lang.currentLang));

                JSONObject jsonObjectLocations = listJSON.getJSONObject("location");
                branch.setLat(jsonObjectLocations.getDouble("lat"));
                branch.setLng(jsonObjectLocations.getDouble("lng"));

                List<WorkDay> workDayList = new ArrayList<>();

                for (int j = 0; j < listJSON.getJSONArray("workhours").length(); j++) {
                    JSONObject jsonobject = listJSON.getJSONArray("workhours").getJSONObject(j);
                    WorkDay workDay = new WorkDay();
                    workDay.setDays(jsonobject.getString("days"));
                    workDay.setHours(jsonobject.getString("hours"));
                    workDayList.add(workDay);
                }
                branch.setWorkhours(workDayList);

                if (branch.getHead() == 1){
                    headBranch = branch;
                } else {
                    branchList.add(branch);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bankData = new BankData(branchList,headBranch);

        return bankData;
    }
}
