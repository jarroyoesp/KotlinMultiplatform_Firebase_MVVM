//
//  ViewController.swift
//  iOSApp
//
//  Created by Javier Arroyo on 08/04/2020.
//  Copyright © 2020 Javier Arroyo. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    
    @IBOutlet weak var mTableView: UITableView!
    @IBOutlet weak var mButtonGetData: UIButton!
    
    private var mFirebaseViewModel: FirebaseViewModel!
    
    // Table View Data
    internal var mFirebaseUserList: [FirebaseUser] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configView()
        initViewModel()
    }
        
    func configView() {
        mButtonGetData.addTarget(self, action: #selector(didButtonClick), for: .touchUpInside)
            
        mTableView.dataSource = self
        mTableView.delegate = self
    }
        
    func initViewModel() {
        mFirebaseViewModel = FirebaseViewModel()
        observeFirebaseViewModel()
    }
    
    func observeFirebaseViewModel() {
        mFirebaseViewModel.mGetFirebaseUserListLiveData.addObserver { (mCurrentState) in
            if (mCurrentState is SuccessGetFirebaseUserListState) {
                let successState = mCurrentState as! SuccessGetFirebaseUserListState
                let response = (successState.response as! Response.Success)
                let userList = response.data as! [FirebaseUser]
                self.onSuccessGetFirebaseUserList(list: userList)
                
            } else if (mCurrentState is LoadingGetFirebaseUserListState) {

            } else if (mCurrentState is ErrorGetFirebaseUserListState) {

            }
            
        }
    }
    
    func onSuccessGetFirebaseUserList(list: [FirebaseUser]) {
        update(list: list)
    }
    
    /****************************************************************************
    * ON CLICKS
    ****************************************************************************/
    @objc func didButtonClick(_ sender: UIButton) {
        mFirebaseViewModel.getFirebaseUserListFlow()
    }
       
    /*****************************************************************************
    TABLE VIEW
    ****************************************************************************/
    internal func update(list: [FirebaseUser]) {
        mFirebaseUserList.removeAll()
        mFirebaseUserList.append(contentsOf: list)
        mTableView.reloadData()
    }
       
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mFirebaseUserList.count
    }
       
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "locationListCell", for: indexPath)
        let entry = mFirebaseUserList[indexPath.row]
           
        cell.textLabel?.text = entry.name
        return cell
    }
       
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let entryNum = mFirebaseUserList[indexPath.row].name
    }
}

