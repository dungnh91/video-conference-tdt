$type = $this->_request->getParam('type', null);
			$page = $this->_request->getParam('p', 1);
			$limit = Site_Module_Config::getConfig('hrm')->common->item_per_page;
			$from = ($page * $limit) - $limit;
			$total = intval($this->slaveCon->countCont());
			$num_page = ceil($total/$limit);
			
			if ($type == 'ajax') {
				$this->_helper->layout()->disableLayout();
				//$this->_helper->getHelper('viewRenderer')->setNoRender();
			}
			
			$search_id = trim($this->_request->getParam('search',null));
			$filter_id = $this->_request->getParam('filter',null);
			$this->view->search_id = $search_id;
			$this->view->filter_id = $filter_id;
			
			if (!empty($search_id) && !isset($filter_id))
			{
				$result = $this->slaveCon->getByStaffId($search_id);
				$num_page = 1;
			}
			else if (empty($search_id) && isset($filter_id))
			{
				$result = $this->slaveCon->getByType($filter_id, $limit, $from);
				if (isset($result))
				{
					$total = count($this->slaveCon->getListByType($filter_id));
					$num_page = ceil($total/$limit);
				}
			}
			else if (!empty($search_id) && isset($filter_id))
			{
				$result = $this->slaveCon->getByIdvType($search_id, $filter_id);
				$num_page = 1;
			}
			else
			{
				$result = $this->slaveCon->getListCont($from, $limit);
			}
			
			while (empty($result))
			{
				$page = $page - 1;
				$from = ($page * $limit) - $limit;
				$result = $this->slaveCon->getListCont($from, $limit);
			}
			
			$this->view->assign('type', $type);
			$this->view->assign('num_page', $num_page);
			$this->view->assign('cur_page', $page);
			$this->view->assign('list', $result);	
			
			//Get Staff
			if (isset($result)) {
				foreach($result as $value)
				{
					$get_boss = $this->slaveConStaff->getById($value->signed_boss);
					if (!empty($get_boss))
						$value->boss_name = $get_boss->lastname.' '.$get_boss->middlename.' '.$get_boss->firstname;
					if (empty($value->staff_id))
					{
						$get_partner = $this->slaveConPartner->getById($value->partner_id);
						if (!empty($get_partner->business_name))
							$value->staff_name = $get_partner->business_name;
					}
					else
					{
						$get_staff = $this->slaveConStaff->getById($value->staff_id);
						if (!empty($get_staff))
							$value->staff_name = $get_staff->lastname.' '.$get_staff->middlename.' '.$get_staff->firstname;
					}
				}
			}