<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Admin Homepage</title>
    <!-- Font Awesome -->
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
    />
    <!-- Google Fonts Roboto -->
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
    />
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css" />
    <!-- Custom styles -->
    <link rel="stylesheet" href="css/admin.css" />
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"
      integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw=="
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <header>
      <!-- Sidebar -->
      <nav
        id="sidebarMenu"
        class="collapse d-lg-block sidebar collapse bg-white"
      >
        <div class="position-sticky">
          <div class="list-group list-group-flush mx-3 mt-4">
            <a
              href="/BankingSystem/staff-dashboard"
              class="list-group-item list-group-item-action py-2 ripple active"
            >
              <i class="fas fa-chart-area fa-fw me-3"></i
              ><span>Main dashboard</span>
            </a>
            <a
              href="/BankingSystem/user-list"
              class="list-group-item list-group-item-action py-2 ripple"
              ><i class="fas fa-users fa-fw me-3"></i
              ><span>Users account</span></a
            >
            <a
              href="/BankingSystem/request-list"
              class="list-group-item list-group-item-action py-2 ripple"
              ><i class="fas fa-building fa-fw me-3"></i
              ><span>Requests list</span></a
            >
          </div>
        </div>
      </nav>
      <!-- Sidebar -->

      <!-- Navbar -->
      <nav
        id="main-navbar"
        class="navbar navbar-expand-lg navbar-light bg-white fixed-top"
      >
        <!-- Container wrapper -->
        <div class="container-fluid">
          <!-- Toggle button -->
          <button
            class="navbar-toggler"
            type="button"
            data-mdb-toggle="collapse"
            data-mdb-target="#sidebarMenu"
            aria-controls="sidebarMenu"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <i class="fas fa-bars"></i>
          </button>

          <!-- Brand -->
          <a class="navbar-brand" href="#">
            <img
              src="images/Staff.png"
              height="25"
              alt=""
              loading="lazy"
            />
          </a>
          <!-- Search form -->
          <form class="d-none d-md-flex input-group w-auto my-auto">
            <input
              autocomplete="off"
              type="search"
              class="form-control rounded"
              placeholder='Search (ctrl + "/" to focus)'
              style="min-width: 225px"
            />
            <span class="input-group-text border-0"
              ><i class="fas fa-search"></i
            ></span>
          </form>

          <!-- Right links -->
          <ul class="navbar-nav ms-auto d-flex flex-row">
            <!-- Notification dropdown -->

            <!-- Icon -->

            <!-- Icon -->
            <li class="nav-item me-3 me-lg-0">
              <a class="nav-link" href="https://github.com/group1SE17A08/BankingSystem">
                <i class="fab fa-github"></i>
              </a>
            </li>

            <!-- Icon dropdown -->

            <!-- Avatar -->
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle hidden-arrow d-flex align-items-center"
                href="#"
                id="navbarDropdownMenuLink"
                role="button"
                data-mdb-toggle="dropdown"
                aria-expanded="false"
              >
                <img
                  src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg"
                  class="rounded-circle"
                  height="22"
                  alt=""
                  loading="lazy"
                />
              </a>
              <ul
                class="dropdown-menu dropdown-menu-end"
                aria-labelledby="navbarDropdownMenuLink"
              >
                <li><a class="dropdown-item" href="/BankingSystem/logout">Logout</a></li>
              </ul>
            </li>
          </ul>
        </div>
        <!-- Container wrapper -->
      </nav>
      <!-- Navbar -->
    </header>
    <!--Main Navigation-->

    <!--Main layout-->
    <main style="margin-top: 58px">
      <div class="container pt-4">
        <!-- Section: Main chart -->

        <!--Section: Sales Performance KPIs-->

        <!--Section: Minimal statistics cards-->
        <section>
          <div class="row">
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div class="align-self-center">
                      <i class="fas fa-pencil-alt text-info fa-3x"></i>
                    </div>
                    <div class="text-end">
                      <h3>278</h3>
                      <p class="mb-0">New User registered</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div class="align-self-center">
                      <i class="far fa-comment-alt text-warning fa-3x"></i>
                    </div>
                    <div class="text-end">
                      <h3>156</h3>
                      <p class="mb-0">New Transaction</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div class="align-self-center">
                      <i class="fas fa-chart-line text-success fa-3x"></i>
                    </div>
                    <div class="text-end">
                      <h3>64.89 %</h3>
                      <p class="mb-0">Bounce Rate</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div class="align-self-center">
                      <i class="fas fa-map-marker-alt text-danger fa-3x"></i>
                    </div>
                    <div class="text-end">
                      <h3>423</h3>
                      <p class="mb-0">Total Visits</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-danger">278</h3>
                      <p class="mb-0">New Savings</p>
                    </div>
                    <div class="align-self-center">
                      <i class="fas fa-rocket text-danger fa-3x"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-success">156</h3>
                      <p class="mb-0">New Clients</p>
                    </div>
                    <div class="align-self-center">
                      <i class="far fa-user text-success fa-3x"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-warning">64.89 %</h3>
                      <p class="mb-0">Conversion Rate</p>
                    </div>
                    <div class="align-self-center">
                      <i class="fas fa-chart-pie text-warning fa-3x"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-info">423</h3>
                      <p class="mb-0">Support Tickets</p>
                    </div>
                    <div class="align-self-center">
                      <i class="far fa-life-ring text-info fa-3x"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-info">278</h3>
                      <p class="mb-0">New Request</p>
                    </div>
                    <div class="align-self-center">
                      <i class="fas fa-book-open text-info fa-3x"></i>
                    </div>
                  </div>
                  <div class="px-md-1">
                    <div class="progress mt-3 mb-1 rounded" style="height: 7px">
                      <div
                        class="progress-bar bg-info"
                        role="progressbar"
                        style="width: 80%"
                        aria-valuenow="80"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-warning">156</h3>
                      <p class="mb-0">New Invoice created</p>
                    </div>
                    <div class="align-self-center">
                      <i class="far fa-comments text-warning fa-3x"></i>
                    </div>
                  </div>
                  <div class="px-md-1">
                    <div class="progress mt-3 mb-1 rounded" style="height: 7px">
                      <div
                        class="progress-bar bg-warning"
                        role="progressbar"
                        style="width: 35%"
                        aria-valuenow="35"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-success">64.89 %</h3>
                      <p class="mb-0">Bounce Rate</p>
                    </div>
                    <div class="align-self-center">
                      <i class="fas fa-mug-hot text-success fa-3x"></i>
                    </div>
                  </div>
                  <div class="px-md-1">
                    <div class="progress mt-3 mb-1 rounded" style="height: 7px">
                      <div
                        class="progress-bar bg-success"
                        role="progressbar"
                        style="width: 60%"
                        aria-valuenow="60"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between px-md-1">
                    <div>
                      <h3 class="text-danger">423</h3>
                      <p class="mb-0">Total Visits</p>
                    </div>
                    <div class="align-self-center">
                      <i class="fas fa-map-signs text-danger fa-3x"></i>
                    </div>
                  </div>
                  <div class="px-md-1">
                    <div class="progress mt-3 mb-1 rounded" style="height: 7px">
                      <div
                        class="progress-bar bg-danger"
                        role="progressbar"
                        style="width: 40%"
                        aria-valuenow="40"
                        aria-valuemin="0"
                        aria-valuemax="100"
                      ></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
        <!--Section: Minimal statistics cards-->

        <!--Section: Statistics with subtitles-->
        <section>
          <div class="row">
            <div class="col-xl-6 col-md-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between p-md-1">
                    <div class="d-flex flex-row">
                      <div class="align-self-center">
                        <i class="fas fa-pencil-alt text-info fa-3x me-4"></i>
                      </div>
                      <div>
                        <h4>Total Amount of transferring money</h4>
                        <p class="mb-0">Monthly</p>
                      </div>
                    </div>
                    <div class="align-self-center">
                      <h2 class="h1 mb-0">18,000</h2>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-6 col-md-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between p-md-1">
                    <div class="d-flex flex-row">
                      <div class="align-self-center">
                        <i
                          class="far fa-comment-alt text-warning fa-3x me-4"
                        ></i>
                      </div>
                      <div>
                        <h4>Total Invoice paid</h4>
                        <p class="mb-0">Monthly</p>
                      </div>
                    </div>
                    <div class="align-self-center">
                      <h2 class="h1 mb-0">865</h2>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xl-6 col-md-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between p-md-1">
                    <div class="d-flex flex-row">
                      <div class="align-self-center">
                        <h2 class="h1 mb-0 me-4">$76,456.00</h2>
                      </div>
                      <div>
                        <h4>Total Amount of money</h4>
                        <p class="mb-0">Monthly</p>
                      </div>
                    </div>
                    <div class="align-self-center">
                      <i class="far fa-heart text-danger fa-3x"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-6 col-md-12 mb-4">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between p-md-1">
                    <div class="d-flex flex-row">
                      <div class="align-self-center">
                        <h2 class="h1 mb-0 me-4">$36,000.00</h2>
                      </div>
                      <div>
                        <h4>Total Cost</h4>
                        <p class="mb-0">Monthly Cost</p>
                      </div>
                    </div>
                    <div class="align-self-center">
                      <i class="fas fa-wallet text-success fa-3x"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
        <!--Section: Statistics with subtitles-->
      </div>
    </main>
    <!--Main layout-->
    <!-- MDB -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
    <!-- Custom scripts -->
    <script type="text/javascript" src="js/admin.js"></script>
  </body>
</html>
