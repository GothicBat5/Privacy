#include "orconfig.h"
#ifdef ENABLE_RESTART_DEBUGGING
#include <stdlib.h>
#endif

int tor_main(int argc, char *argv[]);

int
main(int argc, char *argv[])
{
  int r;
  
#ifdef ENABLE_RESTART_DEBUGGING
  int restart_count = getenv("TOR_DEBUG_RESTART") ? 1 : 0;
 again:
#endif
  r = tor_main(argc, argv);
  if (r < 0 || r > 255) return 1;
    
#ifdef ENABLE_RESTART_DEBUGGING
  else if (r == 0 && restart_count--) goto again;
    
#endif
  else  return r;
}

